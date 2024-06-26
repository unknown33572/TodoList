package org.example.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.todolist.model.TodoEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
  private String id;
  private String title;
  private boolean done;

  public TodoDTO(final TodoEntity entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.done = entity.isDone();
  }

  public static TodoEntity toEntity(final TodoDTO dto) {
    return TodoEntity.builder()
                     .id(dto.getId())
                     .title(dto.getTitle())
                     .done(dto.isDone())
                     .build();
  }
}
