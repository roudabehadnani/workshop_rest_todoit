package se.lexicon.todo_it_api.service;


import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.dto.PersonSmallDto;
import se.lexicon.todo_it_api.form.PersonForm;

import java.util.List;

public interface PersonService {

    PersonSmallDto create(PersonForm personForm);
    PersonSmallDto findById(Integer personId);
    List<PersonSmallDto> findAll();
    List<PersonSmallDto> findIdlePeople();
    PersonSmallDto update(Integer personId, PersonForm personForm);
    Boolean delete(Integer personId);
    PersonSmallDto addTodoItem(Integer personId, Integer todoId);
    PersonSmallDto removeTodoItem(Integer personId, Integer todoId);
}
