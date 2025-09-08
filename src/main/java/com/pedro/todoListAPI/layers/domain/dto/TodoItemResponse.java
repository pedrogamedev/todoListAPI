package com.pedro.todoListAPI.layers.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TodoItemResponse(
        Long id,
        String title,
        String description
){
}
