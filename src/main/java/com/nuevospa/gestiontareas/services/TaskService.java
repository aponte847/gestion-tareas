package com.nuevospa.gestiontareas.services;

import com.nuevospa.gestiontareas.dtos.TaskRequest;
import com.nuevospa.gestiontareas.dtos.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    TaskResponse createTask(TaskRequest request);

    TaskResponse updateTask(Long id, TaskRequest request);

    void deleteTask(Long id);
}
