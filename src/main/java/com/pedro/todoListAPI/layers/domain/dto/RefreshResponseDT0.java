package com.pedro.todoListAPI.layers.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RefreshResponseDT0(
        String newAccessToken) {
}
