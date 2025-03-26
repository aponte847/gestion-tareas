package com.nuevospa.gestiontareas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 2, message = "El título debe tener al menos 2 caracteres")
    private String title;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 2, message = "La descripción debe tener al menos 2 caracteres")
    private String description;

    @NotNull(message = "El estado de la tarea es obligatorio")
    private Long statusId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

}
