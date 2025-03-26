package com.nuevospa.gestiontareas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @RequestMapping("/health")
    public String healthCheck() {
        return "Todo OK aplicacion en linea";
    }
}
