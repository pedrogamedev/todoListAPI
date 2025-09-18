package com.pedro.todoListAPI.layers.domain.dto;

public record RegisterDTO(
        String login,
        String nickname,
        String password
) {
}
