package se.lexicon.todo_it_api.service;

import se.lexicon.todo_it_api.dto.TodoItemSmallDto;
import se.lexicon.todo_it_api.form.TodoItemForm;

import java.time.LocalDate;
import java.util.List;

public interface TodoItemService {

    TodoItemSmallDto create (TodoItemForm todoItemForm);
    TodoItemSmallDto findById (Integer todoItemId);
    List<TodoItemSmallDto> findAll();
    List<TodoItemSmallDto> findByTitle(String title);
    List<TodoItemSmallDto> findAllUnassigned();
    List<TodoItemSmallDto> findAllUnFinishAndOverdue();
    List<TodoItemSmallDto> findByPersonId(Integer personId);
    List<TodoItemSmallDto> findByDoneStatus(Boolean status);
    List<TodoItemSmallDto> findByDeadLineBetween(LocalDate start, LocalDate end);
    List<TodoItemSmallDto> findByDeadLineBefore(LocalDate end);
    List<TodoItemSmallDto> findByDeadLineAfter(LocalDate start);
    TodoItemSmallDto update(Integer todoItemId, TodoItemForm todoItemForm);
    Boolean delete(Integer todoItemId);
}
