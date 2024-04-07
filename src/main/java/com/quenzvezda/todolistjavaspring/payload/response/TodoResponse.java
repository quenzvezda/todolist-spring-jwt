package com.quenzvezda.todolistjavaspring.payload.response;

import com.quenzvezda.todolistjavaspring.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponse {
    private Long id;
    private String title;
    private String description;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
