package com.todo.primefaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.primefaces.model.TodoModel;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, Integer>{

}
