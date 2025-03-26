package com.nuevospa.gestiontareas.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Health", description = "Validar el estado de la aplicacion")
public class HealthCheckController {

    @GetMapping("/health")
    @Operation(
            summary = "Verificar estado de la aplicación",
            description = "Devuelve un mensaje simple confirmando que la API está funcionando correctamente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Aplicación en línea")
            }
    )
    public String healthCheck() {
        return "Todo OK aplicacion en linea";
    }
}
