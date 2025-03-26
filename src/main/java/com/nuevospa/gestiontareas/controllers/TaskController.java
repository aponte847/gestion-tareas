package com.nuevospa.gestiontareas.controllers;

import com.nuevospa.gestiontareas.dtos.TaskRequest;
import com.nuevospa.gestiontareas.dtos.TaskResponse;
import com.nuevospa.gestiontareas.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tareas", description = "Operaciones CRUD sobre tareas")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(
            summary     = "Listar todas las tareas",
            description = "Devuelve una lista de todas las tareas registradas en la base de datos")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    @Operation(
            summary     = "Tarea por ID",
            description = "Devuelve una tarea por el ID registrada en la base de datos")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    @Operation(
            summary     = "Crea una tarea",
            description = "Crea una tarea y la registra en el base de datos")
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskRequest request) {
        TaskResponse created = taskService.createTask(request);
        return ResponseEntity.created(URI.create("/api/tasks/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(
            summary     = "Actualiza una tarea",
            description = "Actualiza una tarea por el ID registrada en la base de datos")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary     = "Elimina una tarea",
            description = "Elimina una tarea por el ID registrada en la base de datos")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
