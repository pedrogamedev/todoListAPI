package com.pedro.todoListAPI.layers.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(

        @NotNull
        @NotBlank
                String login,

        @NotNull
        @NotBlank
        String nickname,

        @NotNull
        @NotBlank
        String password
) {
}
