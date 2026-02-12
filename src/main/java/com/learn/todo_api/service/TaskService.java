package com.learn.todo_api.service;

import com.learn.todo_api.dto.TaskRequestDTO;
import com.learn.todo_api.dto.TaskResponseDTO;
import com.learn.todo_api.model.Task;
import com.learn.todo_api.repository.TaskRepository;
import com.learn.todo_api.utils.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;  // ← Inyectamos el mapper

    // Constructor con ambas dependencias
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    // Obtener todas las tareas → devuelve DTOs
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()                              // ← Convierte List a Stream
                .map(taskMapper::toResponseDTO)        // ← Aplica el mapper a cada elemento
                .collect(Collectors.toList());         // ← Vuelve a convertir a List
    }

    // Obtener una tarea por ID
    public TaskResponseDTO getTaskById(Long id) {
        Task task = findTaskOrThrow(id);  // ← Método auxiliar (ver abajo)
        return taskMapper.toResponseDTO(task);
    }

    // Crear nueva tarea
    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {
        Task task = taskMapper.toEntity(requestDTO);  // ← DTO → Entity
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(savedTask);   // ← Entity → DTO
    }

    // Actualizar tarea existente
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {
        Task existingTask = findTaskOrThrow(id);

        taskMapper.updateEntityFromDTO(requestDTO, existingTask);  // ← Actualiza in-place

        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.toResponseDTO(updatedTask);
    }

    // Eliminar tarea
    public void deleteTask(Long id) {
        Task task = findTaskOrThrow(id);
        taskRepository.delete(task);
    }

    // Buscar por estado
    public List<TaskResponseDTO> getTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed)
                .stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ═══════════════════════════════════════════════════════════
    // MÉTODO AUXILIAR PRIVADO
    // Centraliza la lógica de "buscar o lanzar excepción"
    // ═══════════════════════════════════════════════════════════
    private Task findTaskOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }
}