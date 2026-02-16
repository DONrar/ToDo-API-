package com.learn.todo_api.repository;

import com.learn.todo_api.dto.TaskResponseDTO;
import com.learn.todo_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // ← Le dice a Spring: "esto accede a datos"
public interface TaskRepository extends JpaRepository<Task, Long> {
    // ¡MAGIA! JpaRepository ya incluye:
    // - findAll()
    // - findById(id)
    // - save(entity)
    // - deleteById(id)
    // - count()
    // ... y muchos más

    // Métodos personalizados (Spring los implementa solo por el nombre)
    List<Task> findByCompleted(boolean completed);
    List<Task> findByTitleContainingIgnoreCase(String title);
    boolean existsByTitle(String title);
}