package com.nuevospa.gestiontareas.services.impl;

import com.nuevospa.gestiontareas.dtos.TaskRequest;
import com.nuevospa.gestiontareas.dtos.TaskResponse;
import com.nuevospa.gestiontareas.entities.Task;
import com.nuevospa.gestiontareas.entities.TaskStatus;
import com.nuevospa.gestiontareas.entities.User;
import com.nuevospa.gestiontareas.exceptions.ResourceNotFoundException;
import com.nuevospa.gestiontareas.repositories.TaskRepository;
import com.nuevospa.gestiontareas.repositories.TaskStatusRepository;
import com.nuevospa.gestiontareas.repositories.UserRepository;
import com.nuevospa.gestiontareas.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskStatusRepository statusRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskStatusRepository statusRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskById(id);
        return toResponse(task);
    }

    @Override
    public TaskResponse createTask(TaskRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + request.getUserId()));

        TaskStatus status = statusById(request.getStatusId());

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setUser(user);
        task.setStatus(status);
        task.setCreatedAt(new Date());
        task.setUpdatedAt(null);

        return toResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        TaskStatus status = statusById(request.getStatusId());

        task.setStatus(status);
        task.setUpdatedAt(new Date());

        return toResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarea no encontrada con ID: " + id);
        }
        taskRepository.deleteById(id);
    }


    // Metodos privados
    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().getName(),
                task.getUser().getId(),
                task.getUser().getUsername(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    private Task taskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
    }

    private TaskStatus statusById(Long id){
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + id));
    }


}

