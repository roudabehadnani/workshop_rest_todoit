package se.lexicon.todo_it_api.dto;

import lombok.*;
import se.lexicon.todo_it_api.model.entity.Person;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoItemDto {

    private Integer todoId;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private PersonSmallDto assignee;
}
