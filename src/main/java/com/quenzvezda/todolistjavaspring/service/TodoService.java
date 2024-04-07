package com.quenzvezda.todolistjavaspring.service;

import com.quenzvezda.todolistjavaspring.exception.TodoValidationException;
import com.quenzvezda.todolistjavaspring.model.Todo;
import com.quenzvezda.todolistjavaspring.model.User;
import com.quenzvezda.todolistjavaspring.payload.response.TodoResponse;
import com.quenzvezda.todolistjavaspring.payload.response.UserTodoResponse;
import com.quenzvezda.todolistjavaspring.repository.TodoRepository;
import com.quenzvezda.todolistjavaspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserTodoResponse getAllTodo() {
        User currentUser = getCurrentUser();
        List<Todo> todos = todoRepository.findByUser(currentUser);
        List<TodoResponse> todoResponses = todos.stream()
                .map(todo -> modelMapper.map(todo, TodoResponse.class))
                .collect(Collectors.toList());

        UserTodoResponse response = new UserTodoResponse();
        response.setUserId(currentUser.getId());
        response.setUsername(currentUser.getUsername());
        response.setTodos(todoResponses);

        return response;
    }

    private User getCurrentUser() {
        // Dapatkan username dari SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Cari user berdasarkan username
        return userRepository.findByUsername(username);
    }

    public Todo createTodo(Todo todo) {
        User currentUser = getCurrentUser();
        todo.setUser(currentUser);

        if (todo.getTitle().length() > 100) {
            throw new TodoValidationException("Title length cannot exceed 100 characters.");
        }
        if (todo.getDescription() != null && todo.getDescription().length() > 500) {
            throw new TodoValidationException("Description length cannot exceed 500 characters.");
        }

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));

        // Cek apakah toodo milik user yang sedang login
        if (!todo.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new RuntimeException("You are not authorized to update this todo");
        }

        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setStatus(todoDetails.getStatus());

        if (todo.getTitle().length() > 100) {
            throw new TodoValidationException("Title length cannot exceed 100 characters.");
        }
        if (todo.getDescription() != null && todo.getDescription().length() > 500) {
            throw new TodoValidationException("Description length cannot exceed 500 characters.");
        }

        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));

        // Cek apakah todo milik user yang sedang login
        if (!todo.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new RuntimeException("You are not authorized to delete this todo");
        }

        todoRepository.delete(todo);
    }
}
