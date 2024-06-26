package com.quenzvezda.todolistjavaspring.repository;

import com.quenzvezda.todolistjavaspring.model.Todo;
import com.quenzvezda.todolistjavaspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);

}
