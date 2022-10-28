package se.lexicon.todo_it_api.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.dto.PersonSmallDto;
import se.lexicon.todo_it_api.dto.TodoItemSmallDto;
import se.lexicon.todo_it_api.exception.AppResourceNotFoundException;
import se.lexicon.todo_it_api.form.TodoItemForm;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService{

    private final TodoItemDAO todoItemDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public TodoItemServiceImpl(TodoItemDAO todoItemDAO, ModelMapper modelMapper) {
        this.todoItemDAO = todoItemDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoItemSmallDto create(TodoItemForm todoItemForm) {
        TodoItem todoItem = modelMapper.map(todoItemForm, TodoItem.class);
        TodoItem saveTodoItem = todoItemDAO.save(todoItem);
        TodoItemSmallDto todoItemDto = modelMapper.map(saveTodoItem, TodoItemSmallDto.class);
        return todoItemDto;
    }

    @Override
    public TodoItemSmallDto findById(Integer todoItemId) {
        if (todoItemId == null) throw new IllegalArgumentException("todoItem Id was null");
        TodoItem found = todoItemDAO.findById(todoItemId).orElseThrow(
                ()-> new AppResourceNotFoundException("TodoItem NOT found"));
        TodoItemSmallDto todoItemDto = modelMapper.map(found, TodoItemSmallDto.class);
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findAll() {
        List<TodoItem> allTodoItem = todoItemDAO.findAll();
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findByTitle(String title) {
        List<TodoItem> allTodoItem = todoItemDAO.findByTitleContains(title);
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findAllUnassigned() {
        List<TodoItem> allTodoItem = todoItemDAO.findUnassignedTodoItems();
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findAllUnFinishAndOverdue() {
        List<TodoItem> allTodoItem = todoItemDAO.findAllUnfinishedAndOverdue();
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findByPersonId(Integer personId) {
        List<TodoItem> allTodoItem = todoItemDAO.findByPersonId(personId);
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findByDoneStatus(Boolean status) {
        List<TodoItem> allTodoItem = todoItemDAO.findByDoneStatus(status);
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findByDeadLineBetween(LocalDate start, LocalDate end) {
        List<TodoItem> allTodoItem = todoItemDAO.findByDeadlineBetween(start, end);
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findByDeadLineBefore(LocalDate end) {
        List<TodoItem> allTodoItem = todoItemDAO.findByDeadLineBefore(end);
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public List<TodoItemSmallDto> findByDeadLineAfter(LocalDate start) {
        List<TodoItem> allTodoItem = todoItemDAO.findByDeadlineAfter(start);
        List<TodoItemSmallDto> todoItemDto = modelMapper.map(allTodoItem, new TypeToken<List<TodoItemSmallDto>>(){}.getType());
        return todoItemDto;
    }

    @Override
    public TodoItemSmallDto update(Integer todoItemId, TodoItemForm todoItemForm) {
        if(todoItemId == null) throw new IllegalArgumentException("TodoItem Id is null");
        if(todoItemForm == null) throw new IllegalArgumentException("TodoItem Form is null");
        if (!todoItemDAO.existsById(todoItemId)) throw new AppResourceNotFoundException("There is no todoItem with that todoItem Id in the database");

        TodoItem todoItem = todoItemDAO.findById(todoItemId).get();
        if ((todoItem.getTodoId().equals(todoItemId))){
            todoItem.setTitle(todoItemForm.getTitle());
            todoItem.setDescription(todoItemForm.getDescription());
            todoItem.setDeadLine(todoItemForm.getDeadLine());
            todoItem.setDone(todoItemForm.isDone());
            todoItem = todoItemDAO.save(todoItem);
        }
        TodoItemSmallDto todoItemDto = modelMapper.map(todoItem, TodoItemSmallDto.class);
        return todoItemDto;
    }

    @Override
    public Boolean delete(Integer todoItemId) {
        if (todoItemId == null) throw new IllegalArgumentException("TodoItem Id Can NOT be Null");
        if(!todoItemDAO.existsById(todoItemId)) throw new AppResourceNotFoundException("There is no TodoItem with that personId in the database");
        todoItemDAO.deleteById(todoItemId);
        return true;
    }
}
