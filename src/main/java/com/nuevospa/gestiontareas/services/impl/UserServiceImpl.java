package com.nuevospa.gestiontareas.services.impl;

import com.nuevospa.gestiontareas.entities.User;
import com.nuevospa.gestiontareas.exceptions.ResourceNotFoundException;
import com.nuevospa.gestiontareas.repositories.UserRepository;
import com.nuevospa.gestiontareas.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        System.out.println("Contraseña: " + user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword())); // esta linea se comenta para que no se encripte la contraseña para pruebas
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }
        if(user.get().getRole().equals("ADMIN")){
            throw new ResourceNotFoundException("No se puede eliminar un usuario administrador");
        }
        userRepository.deleteById(id);
    }
}
