package com.pedro.todoListAPI.layers.domain.dto;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Sort;

public record PageableRequestDTO(
        String page,
        String size,
        Sort sort
) {
}
