package com.quenzvezda.todolistjavaspring.controller;

import com.quenzvezda.todolistjavaspring.model.Todo;
import com.quenzvezda.todolistjavaspring.payload.response.TodoResponse;
import com.quenzvezda.todolistjavaspring.payload.response.UserTodoResponse;
import com.quenzvezda.todolistjavaspring.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todolist")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public UserTodoResponse getAllTodo() {
        return todoService.getAllTodo();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        return todoService.updateTodo(id, todoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleleTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}
