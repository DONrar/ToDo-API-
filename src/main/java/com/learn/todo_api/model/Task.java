package com.learn.todo_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity                          // ← Le dice a Spring: "esto es una tabla"
@Table(name = "tasks")           // ← Nombre de la tabla en BD
public class Task {

    @Id                          // ← Clave primaria1
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ← Auto-increment
    private Long id;

    @Column(nullable = false) // ← No puede ser NULL
    private String title;

    private String description;

    @Column(nullable = false)
    private boolean completed = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer priority = 3; // Default: prioridad media
    // Se ejecuta ANTES de guardar en BD
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // === GETTERS Y SETTERS ===
    // (Si usas Lombok, solo pon @Data arriba y borra todo esto)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
}