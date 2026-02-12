package com.learn.todo_api.dto;

import jakarta.validation.constraints.*;

// Este DTO es para CREAR o ACTUALIZAR tareas
// Solo contiene los campos que el cliente puede enviar
public class TaskRequestDTO {

    @NotBlank(message = "El título es obligatorio")  // ← No puede ser null ni vacío
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String title;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    // Para crear: no enviamos 'completed' (default false)
    // Para actualizar: sí podemos enviarlo
    private Boolean completed;

    @Min(value = 1, message = "La prioridad mínima es 1")
    @Max(value = 5, message = "La prioridad máxima es 5")
    @NotNull(message = "La prioridad no puede ser nula")
    private Integer priority;

    // === GETTERS Y SETTERS ===

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
}