package com.pedro.todoListAPI.layers.service.mapper;

import com.pedro.todoListAPI.layers.domain.dto.TodoItemRequest;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemResponse;
import com.pedro.todoListAPI.layers.domain.model.TodoItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoItemMapper {
    public TodoItem toTodoItem(TodoItemRequest request){
        return new TodoItem(
                request.title(),
                request.description()
        );
    }

    public TodoItemResponse toTodoItemResponse(TodoItem todoItem){
        return new TodoItemResponse(
                todoItem.getId(),
                todoItem.getTitle(),
                todoItem.getDescription()
        );
    }

    public List<TodoItemResponse> toTodoItemResponseList(List<TodoItem> list){
        List<TodoItemResponse> listResult = new ArrayList<>();

        for(int i = 0; i<= list.size()-1; i++){
            listResult.add(toTodoItemResponse(list.get(i)));
        }

        return listResult;
    }
}
