package org.example.todolist.controller;

import org.example.todolist.dto.ResponseDTO;
import org.example.todolist.dto.TodoDTO;
import org.example.todolist.model.TodoEntity;
import org.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class TodoController {
  @Autowired
  private TodoService todoService;

  @GetMapping("/test")
  public ResponseEntity<?> testTodo() {
    String str = todoService.testService();
    List<String> list = new ArrayList<>();
    list.add(str);
    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
    try {
      String tempUserId = "temporary-user";

      TodoEntity entity = TodoDTO.toEntity(dto);

      entity.setId(null);

      entity.setUserId(tempUserId);

      List<TodoEntity> entities = todoService.create(entity);

      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      String error = ex.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping
  public ResponseEntity<?> retrieveTodoList() {
    String temporaryUserId = "temporary-user";

    List<TodoEntity> entities = todoService.retrieve(temporaryUserId);

    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

    return ResponseEntity.ok(response);
  }

  @PutMapping
  public ResponseEntity<?> updateTodoList(@RequestBody TodoDTO dto) {
    try {
      String tempUserId = "temporary-user";

      TodoEntity entity = TodoDTO.toEntity(dto);

      entity.setUserId(tempUserId);

      List<TodoEntity> entities = todoService.update(entity);

      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      String error = ex.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }
}
