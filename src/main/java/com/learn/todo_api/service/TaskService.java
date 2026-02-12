package com.learn.todo_api.service;

import com.learn.todo_api.dto.TaskResponseDTO;
import com.learn.todo_api.model.Task;
import com.learn.todo_api.repository.TaskRepository;
import com.learn.todo_api.utils.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service  // ← Le dice a Spring: "esto contiene lógica de negocio"
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    // INYECCIÓN POR CONSTRUCTOR (la forma recomendada)
    // Spring ve que necesitas TaskRepository y te lo "inyecta"
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    // Obtener todas las tareas
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Obtener una tarea por ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }

    // Crear nueva tarea
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Actualizar tarea existente
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);  // Reutilizamos el método

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(task);
    }

    // Eliminar tarea
    public void deleteTask(Long id) {
        Task task = getTaskById(id);  // Verificamos que existe
        taskRepository.delete(task);
    }

    // Buscar tareas completadas o pendientes
    public List<Task> getTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }
}