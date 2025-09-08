package com.pedro.todoListAPI.layers.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TodoItemRequest(
        @NotBlank @NotNull String title,
         String description
) {
}
