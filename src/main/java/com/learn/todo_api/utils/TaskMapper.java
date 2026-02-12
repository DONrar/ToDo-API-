package com.learn.todo_api.utils;

import com.learn.todo_api.dto.TaskRequestDTO;
import com.learn.todo_api.dto.TaskResponseDTO;
import com.learn.todo_api.model.Task;
import org.springframework.stereotype.Component;

@Component  // ← Spring lo gestiona, podemos inyectarlo
public class TaskMapper {

    // Convierte Entity → ResponseDTO (para enviar al cliente)
    public TaskResponseDTO toResponseDTO(Task task) {
        if (task == null) return null;

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getPriority(),
                task.getCreatedAt()
        );
    }

    // Convierte RequestDTO → Entity (para guardar en BD)
    public Task toEntity(TaskRequestDTO dto) {
        if (dto == null) return null;

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());

        // Si viene 'completed', lo seteamos; si no, queda el default (false)
        if (dto.getCompleted() != null) {
            task.setCompleted(dto.getCompleted());
        }

        return task;
    }

    // Actualiza una Entity existente con datos del DTO
    // Esto evita crear un objeto nuevo y perder el ID
    public void updateEntityFromDTO(TaskRequestDTO dto, Task task) {
        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }
        if (dto.getCompleted() != null) {
            task.setCompleted(dto.getCompleted());
        }
        if (dto.getPriority() != null) {
            task.setPriority(dto.getPriority());
        }
    }
}