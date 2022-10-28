package se.lexicon.todo_it_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todo_it_api.dto.TodoItemSmallDto;
import se.lexicon.todo_it_api.form.TodoItemForm;
import se.lexicon.todo_it_api.service.TodoItemServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todo/api/v1/items")
public class TodoItemController {

    private final TodoItemServiceImpl todoItemService;

    @Autowired
    public TodoItemController(TodoItemServiceImpl todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public ResponseEntity<TodoItemSmallDto> create(@RequestBody TodoItemForm todoItemForm){
        TodoItemSmallDto createTodoItem = todoItemService.create(todoItemForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTodoItem);
    }

    @GetMapping
    public ResponseEntity<List<TodoItemSmallDto>> findAll(){
        List<TodoItemSmallDto> all = todoItemService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItemSmallDto> findByTodoItemId(@PathVariable("id") Integer todoItemId){
        TodoItemSmallDto found = todoItemService.findById(todoItemId);
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer todoItemId){
        todoItemService.delete(todoItemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItemSmallDto> update(@PathVariable("id") Integer todoItemId,@RequestBody TodoItemForm todoItemForm){
        TodoItemSmallDto updateTodoItem = todoItemService.update(todoItemId, todoItemForm);
        return ResponseEntity.ok(updateTodoItem);
    }

//    @GetMapping("/date")
//    public ResponseEntity<List<TodoItemSmallDto>> find(@RequestParam("date") LocalDate end){
//        List<TodoItemSmallDto> byDeadLineBefore = todoItemService.findByDeadLineBefore(end);
//        return ResponseEntity.ok(byDeadLineBefore);
//    }
}
