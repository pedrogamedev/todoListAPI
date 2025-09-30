package com.pedro.todoListAPI.layers.service.services;

import com.pedro.todoListAPI.layers.domain.dto.PageableRequestDTO;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemRequest;
import com.pedro.todoListAPI.layers.domain.dto.TodoItemResponse;
import com.pedro.todoListAPI.layers.domain.model.TodoItem;
import com.pedro.todoListAPI.layers.repository.TodoListRepository;
import com.pedro.todoListAPI.layers.service.mapper.PageableMapper;
import com.pedro.todoListAPI.layers.service.mapper.TodoItemMapper;
import com.pedro.todoListAPI.miscelaneous.exceptions.EmptyDatabaseException;
import com.pedro.todoListAPI.miscelaneous.exceptions.PageNotFoundException;
import com.pedro.todoListAPI.miscelaneous.exceptions.TermNotFoundException;
import com.pedro.todoListAPI.miscelaneous.exceptions.TodoItemNotFoundException;
import com.pedro.todoListAPI.miscelaneous.utils.NullUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

    @Autowired
    TodoListRepository repository;

    @Autowired
    TodoItemMapper todoItemMapper;

    @Autowired
    PageableMapper pageableMapper;

    @Transactional
    public TodoItemResponse saveTodoItem(TodoItemRequest request){
        return todoItemMapper.toTodoItemResponse(repository.save(todoItemMapper.toTodoItem(request)));
    }

    public TodoItemResponse getTodoItemById(Long id){
        TodoItem todoItem = repository.findById(id)
                .orElseThrow(() -> new TodoItemNotFoundException(id));

        return todoItemMapper.toTodoItemResponse(todoItem);
    }

    public List<TodoItemResponse> getAllTodoItemsByTerm(String term){
        List<TodoItemResponse> responses = todoItemMapper.toTodoItemResponseList(repository.findByTerm(term));
        if(responses.isEmpty())
        {
            throw new TermNotFoundException(term);
        }
                return responses;
    }

    public List<TodoItemResponse> getAllTodoItems(){
        List<TodoItemResponse> responses = todoItemMapper.toTodoItemResponseList(repository.findAll());
        if(responses.isEmpty()){
            throw new EmptyDatabaseException();
        }
        return responses;
    }
    
    public Page<TodoItemResponse> getAllTodoItemsPaged(PageableRequestDTO dto) {


        Pageable pageable =pageableMapper.toPageable(dto);

        Page<TodoItem> page = repository.findAll(pageable);
        if (pageable.getPageNumber() > page.getTotalPages() -1)
        {
            throw new PageNotFoundException(pageable.getPageNumber());
        }
        if (page.isEmpty()){
            throw new EmptyDatabaseException();
        }
        return todoItemMapper.toTodoItemResponsePage(page);
    }


    @Transactional
    public TodoItemResponse updateTodoItem(TodoItemRequest request, Long id){
        return repository.findById(id)
                .map(newTodoItem ->{
                    newTodoItem.setTitle(request.title());
                    NullUtils.updateIfPresent(newTodoItem::setDescription, request.description());
                    return todoItemMapper.toTodoItemResponse(newTodoItem);
                }).orElseThrow(() -> new TodoItemNotFoundException(id));
    }



    @Transactional
    public void deleteById(Long id){
        if(repository.deleteByIdCustom(id) <= 0L){
            throw new TodoItemNotFoundException(id);
        }
    }



}
