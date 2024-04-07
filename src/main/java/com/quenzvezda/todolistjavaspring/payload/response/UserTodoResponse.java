package com.quenzvezda.todolistjavaspring.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class UserTodoResponse {
    private Long userId;
    private String username;
    private List<TodoResponse> todos;
}
