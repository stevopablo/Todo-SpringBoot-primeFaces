package com.todo.primefaces.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.primefaces.model.TodoModel;
import com.todo.primefaces.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

   public List<TodoModel> getAllTasks(){
    return todoRepository.findAll();
   }

   public String addTask(TodoModel todo){
    if(todo.getTask().isEmpty() && todo.getProgress().isEmpty()){
        return "field required (Task, Progress)";
    }
    todoRepository.save(todo);
    return todo.getId() + todo.getTask() + todo.getProgress();
   }

   public String updateTask(int id, TodoModel todo){
    Optional<TodoModel> todoExist = todoRepository.findById(id);
    if (todoExist.isPresent()) {
        TodoModel todoUpdated = todoExist.get();
        todoUpdated.setProgress(todo.getProgress());
        todoUpdated.setTask(todo.getTask());
        todoRepository.save(todoUpdated);
        return "Task updated successfully";
    }
    return "task not found" + todo.getId();
    
   }

   public String deleteTask(int id){
    Optional<TodoModel> task = todoRepository.findById(id);
    if (task.isPresent()) {
        todoRepository.deleteById(id);
        return "Task deleted successfully";
    }else{
        return "Task not found";
    }
   }

   public TodoModel getTaskById(int id){
      return todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Task not found"));
    }

}