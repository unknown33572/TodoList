package org.example.todolist.persistence;

import org.example.todolist.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
  @Query("select t from TodoEntity t where t.userId = ?1")
  List<TodoEntity> findByUserId(String userId);
}
