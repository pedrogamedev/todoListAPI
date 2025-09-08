package com.pedro.todoListAPI.layers.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "todoList")
@Table(name = "todoList")
public class TodoItem {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    public TodoItem(String title, String description){
        this.title = title;
        this.description = description;
    }
}
