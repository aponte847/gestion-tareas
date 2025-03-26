package com.nuevospa.gestiontareas.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Health", description = "Validar el estado de la aplicacion")
public class HealthCheckController {

    @RequestMapping("/health")
    public String healthCheck() {
        return "Todo OK aplicacion en linea";
    }
}
