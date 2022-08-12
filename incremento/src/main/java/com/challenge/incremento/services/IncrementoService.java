package com.challenge.incremento.services;

import com.challenge.incremento.dtos.PorcentajeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.util.retry.Retry;
import java.time.Duration;

@Service
@Slf4j
@EnableScheduling
public class IncrementoService {

    @Value("${BASE_PATH}")
    private String basePath;
    @Value("${INCREMENTO_PATH}")
    private String incrementoPath;
    @Value("${MAX_RETRY}")
    private Integer maxRetry;
    @Value("${DURATION_REQUEST}")
    private Integer durationRequest;

    //TODO meter valor en base de datos..
    private Double porcentaje = null;

    @Autowired
    WebClient.Builder webClient;

    public Double calcularIncremento(Double valor1, Double valor2) {
        validarPorcentaje();
        return valor1 + valor2 * porcentaje;
    }

    @Scheduled(cron = "0 0/30 * * * *")
    public void obtenerPorcentaje() {
        PorcentajeDTO porcentaje = webClient.build()
                .get()
                .uri(basePath + incrementoPath)
                .retrieve()
                .bodyToFlux(PorcentajeDTO.class)
                .retryWhen(Retry.backoff(maxRetry, Duration.ofMillis(durationRequest))
                        .filter(throwable -> throwable instanceof WebClientRequestException)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure()))
                .blockFirst();


        if (existeNuevoPorcentaje(porcentaje)) {
            this.porcentaje = 1.0 + (porcentaje.getPorcentaje() / 100);
        }
    }

    private void validarPorcentaje() {
        if (this.porcentaje == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No es posible calcular el incremento");
        }
    }

    private boolean existeNuevoPorcentaje(PorcentajeDTO porcentaje) {
        return porcentaje != null && porcentaje.getPorcentaje() != null;
    }

}
