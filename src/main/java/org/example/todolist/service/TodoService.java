package org.example.todolist.service;

import lombok.extern.slf4j.Slf4j;
import org.example.todolist.model.TodoEntity;
import org.example.todolist.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {
  @Autowired
  private TodoRepository repository;

  public String testService() {
//    return "Test Service";
    TodoEntity entity = TodoEntity.builder().title("My first todo item").build();

    repository.save(entity);

    TodoEntity savedEntity = repository.findById(entity.getId()).get();

    return savedEntity.getTitle();
  }

  public List<TodoEntity> create(final TodoEntity entity) {
    validate(entity);

    repository.save(entity);

    log.info("Entity ID: {} is saved.", entity.getId());

    return repository.findByUserId(entity.getUserId());
  }

  private void validate(final TodoEntity entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Entity cannot be null");
    }

    if (entity.getUserId() == null) {
      log.warn("User ID is null");
      throw new IllegalArgumentException("User ID cannot be null");
    }
  }

  public List<TodoEntity> retrieve(final String userId) {
    return repository.findByUserId(userId);
  }

  public List<TodoEntity> update(final TodoEntity entity) {
    validate(entity);

    final Optional<TodoEntity> original = repository.findById(entity.getId());

    original.ifPresent(todoEntity -> {
      todoEntity.setTitle(entity.getTitle());
      todoEntity.setDone(entity.isDone());
      repository.save(todoEntity);
    });

    return retrieve(entity.getUserId());
  }

  public List<TodoEntity> delete(final TodoEntity entity) {
    validate(entity);

    try {
      repository.delete(entity);
    } catch (Exception ex) {
      log.error("Error deleting entity with ID: {}", entity.getId(), ex);
      throw new IllegalArgumentException("Error deleting entity");
    }
    return retrieve(entity.getUserId());
  }

}
