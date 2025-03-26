package com.nuevospa.gestiontareas.controllers;


import com.nuevospa.gestiontareas.entities.User;
import com.nuevospa.gestiontareas.exceptions.ResourceNotFoundException;
import com.nuevospa.gestiontareas.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones CRUD sobre usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(
            summary     = "Usuarios",
            description = "Devuelve una lista de todos los usuarios registrados en la base de datos")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary     = "Usuario por ID",
            description = "Devuelve un usuario por el ID registrado en la base de datos")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @PostMapping
    @Operation(
            summary     = "Crear usuario",
            description = "Crea un usuario y lo registra en la base de datos")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.save(user);
        return ResponseEntity.created(URI.create("/api/users"+ created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(
            summary     = "Actulizar usuario",
            description = "Actuliza un usuario por el ID y lo registra en la base de datos")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(updatedUser.getPassword());
                    user.setRole(updatedUser.getRole());
                    return ResponseEntity.ok(userService.save(user));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary     = "Eliminar usuario",
            description = "Elimina un usuario por el ID y lo registra en la base de datos")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
