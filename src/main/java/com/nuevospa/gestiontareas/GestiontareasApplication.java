package com.nuevospa.gestiontareas;

import com.nuevospa.gestiontareas.entities.User;
import com.nuevospa.gestiontareas.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestiontareasApplication {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

    public GestiontareasApplication(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(GestiontareasApplication.class, args);
	}

	@PostConstruct
	public void createUserAdmin() {
		userRepository.deleteAll();

		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin123"));
		user.setRole("ADMIN");

		userRepository.save(user);

		System.out.println("Usuario admin creado desde código");
	}

	@PostConstruct
	public void verifyUser() {
		userRepository.findAll().forEach(user -> {
			System.out.println("Usuario encontrado: " + user.getUsername());
			System.out.println("Password almacenado: " + user.getPassword());
			System.out.println("¿Password válido? " + passwordEncoder.matches("admin123", user.getPassword()));
		});

	}

	@PostConstruct
	public void passwordEncoderType() {
		System.out.println("Encoder en uso: " + passwordEncoder.getClass().getSimpleName());
	}



}
