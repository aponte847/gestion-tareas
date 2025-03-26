package com.nuevospa.gestiontareas.services;

import com.nuevospa.gestiontareas.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    User save(User user);
    void delete(Long id);
}
