package com.challenge.auditoriarequest.services;

import com.challenge.auditoriarequest.entities.AuditoriaRequestEntity;
import com.challenge.auditoriarequest.repositories.AuditarRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditarRequestService {

    @Autowired
    AuditarRequestRepository auditarRequestRepository;

    public void auditarRequest(String uri, Integer status, String response, String username) {
        AuditoriaRequestEntity auditoria = new AuditoriaRequestEntity();
        auditoria.setUri(uri);
        auditoria.setStatus(status);
        auditoria.setResponse(response);
        auditoria.setUsername(username);
        auditarRequestRepository.save(auditoria);
    }

}
