package com.challenge.auditoriarequest.controllers;

import com.challenge.auditoriarequest.dtos.RegistroAuditoriaRequestDTO;
import com.challenge.auditoriarequest.dtos.ResponseDTO;
import com.challenge.auditoriarequest.services.AuditarRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditoriaRequestController {

    @Autowired
    AuditarRequestService auditarRequestService;

    @RequestMapping(value = "/auditar-request", method = RequestMethod.GET)
    public ResponseEntity<?> auditarRequest(@RequestBody RegistroAuditoriaRequestDTO body) {
        auditarRequestService.auditarRequest(body.getUri(), body.getStatus(), body.getResponse(), body.getUsername());
        return new ResponseEntity<>(ResponseDTO.builder().mensaje("Request auditada correctamente"), HttpStatus.OK);
    }
}
