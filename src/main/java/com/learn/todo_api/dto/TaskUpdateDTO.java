package com.learn.todo_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDTO {
    @NotNull(message = "el titulo no puede ser nulo")
    @Size(min = 2, max = 40)
    private String title;

    private String description;

    @NotNull(message = "el estado no puede ser nulo")
    private boolean completed;
    private Integer priority;
}
