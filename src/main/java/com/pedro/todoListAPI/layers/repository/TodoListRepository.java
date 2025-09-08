package com.pedro.todoListAPI.layers.repository;

import com.pedro.todoListAPI.layers.domain.model.TodoItem;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoItem, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM todoList b WHERE b.id = :id")
    int deleteByIdCustom(@Param("id") Long id);

}
