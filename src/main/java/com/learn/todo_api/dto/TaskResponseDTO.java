package com.learn.todo_api.dto;

import java.time.LocalDateTime;

// Este DTO es para MOSTRAR tareas al cliente
// Controlamos exactamente qué campos ve el cliente
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Integer priority;
    private LocalDateTime createdAt;

    // Campo calculado que NO está en la entidad
    private String status;  // "PENDIENTE" o "COMPLETADA"

    // === CONSTRUCTOR VACÍO (necesario para Jackson) ===
    public TaskResponseDTO() {}

    // === CONSTRUCTOR COMPLETO (útil para mapear) ===
    public TaskResponseDTO(Long id, String title, String description,
                           boolean completed, Integer priority, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.priority = priority;
        this.createdAt = createdAt;
        this.status = completed ? "COMPLETADA" : "PENDIENTE";  // ← Campo calculado
    }

    // === GETTERS Y SETTERS ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) {
        this.completed = completed;
        this.status = completed ? "COMPLETADA" : "PENDIENTE";
    }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}