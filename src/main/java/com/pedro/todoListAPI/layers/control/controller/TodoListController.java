package com.pedro.todoListAPI.layers.control.controller;


import com.pedro.todoListAPI.layers.control.assembler.TodoItemResponseModelAssembler;
import com.pedro.todoListAPI.layers.domain.dto.PageableRequestDTO;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemRequest;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemResponse;
import com.pedro.todoListAPI.layers.service.services.TodoListService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Validated
@RestController
@RequestMapping("/api")
public class TodoListController {

    @Autowired
    TodoListService service;

    @Autowired
    TodoItemResponseModelAssembler assembler;

    @Autowired
    PagedResourcesAssembler<TodoItemResponse> assemblerPaged;

    @PostMapping("/todoList")
    public ResponseEntity<EntityModel<TodoItemResponse>> saveTodoItem(@Valid @RequestBody TodoItemRequest request){
        return ResponseEntity.ok().body(assembler.toModel(service.saveTodoItem(request)));
    }

    @GetMapping("/todoList")
    public ResponseEntity<EntityModel<TodoItemResponse>> getTodoItem(
            @RequestParam(name = "id") Long id)
    {
        return ResponseEntity.ok().body(assembler.toModel(service.getTodoItemById(id)));
    }
    @GetMapping("/todoList/getAllByTerm")
    public ResponseEntity<CollectionModel<EntityModel<TodoItemResponse>>> getAllTodoItemsByTerm(@Valid @RequestParam @NotNull @NotBlank String term){
        return ResponseEntity.ok().body(assembler.toCollectionModel(service.getAllTodoItemsByTerm(term)));
    }

    @GetMapping("/todoList/getAll")
    public ResponseEntity<CollectionModel<EntityModel<TodoItemResponse>>> getAllTodoItems() {
        return ResponseEntity.ok().body(assembler.toCollectionModel(service.getAllTodoItems()));
    }

    @GetMapping("/todoList/getPaged")
    public ResponseEntity<PagedModel<EntityModel<TodoItemResponse>>> getAllTodoItems(
            @RequestParam(defaultValue = "1", required = true) @Pattern(regexp = "^(0|[1-9][0-9]*)$") String page,
            @RequestParam(defaultValue = "1", required = false) @Pattern(regexp = "^(0|[1-9][0-9]*)$") String size,
            @RequestParam(required = false) Sort sort
            ) {
        PagedModel<EntityModel<TodoItemResponse>> response = assemblerPaged.toModel(
                service.getAllTodoItemsPaged(new PageableRequestDTO(page,size,sort)), assembler
        );
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/todoList")
    public ResponseEntity<EntityModel<TodoItemResponse>> updateTodoItem(
            @RequestParam(name = "id") Long id, @Valid @RequestBody TodoItemRequest request){
        return ResponseEntity.ok().body(assembler.toModel(service.updateTodoItem(request, id)));
    }

    @DeleteMapping("/todoList")
    public ResponseEntity<Void> deleteTodoItem( @Valid @RequestParam(name = "id") @NotNull Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
