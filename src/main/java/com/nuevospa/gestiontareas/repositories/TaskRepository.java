package com.nuevospa.gestiontareas.repositories;

import com.nuevospa.gestiontareas.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
