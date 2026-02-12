package com.learn.todo_api.utils;

import com.learn.todo_api.dto.TaskRequestDTO;
import com.learn.todo_api.dto.TaskResponseDTO;
import com.learn.todo_api.dto.TaskUpdateDTO;
import com.learn.todo_api.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

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

    public Task toEntity(TaskRequestDTO taskRequestDTO) {
        if(taskRequestDTO == null) return null;

        Task task = new Task();

        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        if (taskRequestDTO.getCompleted() != null){
            task.setCompleted(taskRequestDTO.getCompleted());
        }
        task.setPriority(taskRequestDTO.getPriority());

        return task;
    }

     
}
