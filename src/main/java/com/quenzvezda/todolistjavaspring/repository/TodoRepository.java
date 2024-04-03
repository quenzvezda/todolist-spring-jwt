package com.quenzvezda.todolistjavaspring.repository;

import com.quenzvezda.todolistjavaspring.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
