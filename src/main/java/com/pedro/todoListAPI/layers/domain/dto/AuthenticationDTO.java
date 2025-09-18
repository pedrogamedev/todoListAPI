package com.pedro.todoListAPI.layers.domain.dto;

public record AuthenticationDTO(
        String login,
        String password
) {
}
