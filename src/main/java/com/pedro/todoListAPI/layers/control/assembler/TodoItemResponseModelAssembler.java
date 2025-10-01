package com.pedro.todoListAPI.layers.control.assembler;

import com.pedro.todoListAPI.layers.control.controller.TodoListController;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemRequest;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemResponse;
import com.pedro.todoListAPI.layers.domain.model.TodoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TodoItemResponseModelAssembler implements RepresentationModelAssembler<TodoItemResponse, EntityModel<TodoItemResponse>> {
    @Override
    public EntityModel<TodoItemResponse> toModel(TodoItemResponse entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(TodoListController.class).getTodoItem(entity.id())).withSelfRel(),
                linkTo(methodOn(TodoListController.class).getAllTodoItems()).withRel("getAll"),
                linkTo(methodOn(TodoListController.class).deleteTodoItem(entity.id())).withRel("delete"),
                linkTo(methodOn(TodoListController.class).updateTodoItem(entity.id(),
                                new TodoItemRequest("title", "content"))).withRel("update"),
                linkTo(methodOn(TodoListController.class).getAllTodoItemsByTerm("term")).withRel("getAllByTerm"),
                linkTo(methodOn(TodoListController.class).getAllTodoItems(PageRequest.of(1,2))).withRel("getAllPaged"));
    }

    @Override
    public CollectionModel<EntityModel<TodoItemResponse>> toCollectionModel(Iterable<? extends TodoItemResponse> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
