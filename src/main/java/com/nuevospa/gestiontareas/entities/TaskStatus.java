package com.nuevospa.gestiontareas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Task_status")
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public TaskStatus() {
    }

    public TaskStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
