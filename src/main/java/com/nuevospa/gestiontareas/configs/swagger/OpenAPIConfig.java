package com.nuevospa.gestiontareas.configs.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Tareas - NUEVO SPA")
                        .version("1.0.0")
                        .description("API RESTful con autenticación JWT y documentación Swagger")
                );
    }
}
