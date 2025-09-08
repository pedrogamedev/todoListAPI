package com.pedro.todoListAPI.layers.control.controller;


import com.pedro.todoListAPI.layers.control.assembler.TodoItemResponseModelAssembler;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemRequest;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemResponse;
import com.pedro.todoListAPI.layers.service.services.TodoListService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoListController {

    @Autowired
    TodoListService service;

    @Autowired
    TodoItemResponseModelAssembler assembler;

    @PostMapping("/todoList")
    public ResponseEntity<EntityModel<TodoItemResponse>> saveBlogPost(@Valid @RequestBody TodoItemRequest request){
        return ResponseEntity.ok().body(assembler.toModel(service.saveTodoItem(request)));
    }

    @GetMapping("/todoList")
    public ResponseEntity<EntityModel<TodoItemResponse>> getBlogPost(
            @RequestParam(name = "id") Long id)
    {
        return ResponseEntity.ok().body(assembler.toModel(service.getTodoItemById(id)));
    }

    @GetMapping("/todoList/all")
    public ResponseEntity<CollectionModel<EntityModel<TodoItemResponse>>> getAllBlogPosts() {
        return ResponseEntity.ok().body(assembler.toCollectionModel(service.getAllTodoItems()));
    }

    @PatchMapping("/todoList")
    public ResponseEntity<EntityModel<TodoItemResponse>> updateBlogPost(
            @RequestParam(name = "id") Long id, @Valid @RequestBody TodoItemRequest request){
        return ResponseEntity.ok().body(assembler.toModel(service.updateTodoItem(request, id)));
    }

    @DeleteMapping("/todoList")
    public ResponseEntity<Void> deleteBlogPost( @NotNull @RequestParam(name = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
