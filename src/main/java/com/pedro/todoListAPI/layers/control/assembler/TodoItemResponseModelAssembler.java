package com.pedro.todoListAPI.layers.control.assembler;

import com.pedro.todoListAPI.layers.domain.dto.TodoItemResponse;
import com.pedro.todoListAPI.layers.domain.model.TodoItem;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TodoItemResponseModelAssembler implements RepresentationModelAssembler<TodoItemResponse, EntityModel<TodoItemResponse>> {
    @Override
    public EntityModel<TodoItemResponse> toModel(TodoItemResponse entity) {
        return EntityModel.of(entity);
    }

    @Override
    public CollectionModel<EntityModel<TodoItemResponse>> toCollectionModel(Iterable<? extends TodoItemResponse> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
