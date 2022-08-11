package com.challenge.autenticacion.controllers;

import com.challenge.autenticacion.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ResponseEntity<?> login() {
        return null;
    }

    @RequestMapping(value = "/sing-up" , method = RequestMethod.POST)
    public ResponseEntity<?> singUp() {
        return null;
    }

}
