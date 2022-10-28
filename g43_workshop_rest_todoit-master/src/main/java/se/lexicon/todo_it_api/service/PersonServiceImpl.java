package se.lexicon.todo_it_api.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todo_it_api.data.PersonDAO;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.dto.PersonSmallDto;
import se.lexicon.todo_it_api.exception.AppResourceNotFoundException;
import se.lexicon.todo_it_api.form.PersonForm;
import se.lexicon.todo_it_api.model.entity.Person;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService{
    
    private final PersonDAO personDAO;
    private final TodoItemDAO todoItemDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO, TodoItemDAO todoItemDAO, ModelMapper modelMapper) {
        this.personDAO = personDAO;
        this.todoItemDAO = todoItemDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonSmallDto create(PersonForm personForm) {
        if (personForm == null) throw new IllegalArgumentException("Person Can NOT be Null");
        Person person = modelMapper.map(personForm, Person.class);
        Person savePerson = personDAO.save(person);
        PersonSmallDto personDto = modelMapper.map(savePerson, PersonSmallDto.class);
        return personDto;
    }

    @Override
    public PersonSmallDto findById(Integer personId) {
        if (personId == null) throw new IllegalArgumentException("Person Id Can NOT be Null");
        Person foundPersonById = personDAO.findById(personId).orElseThrow(
                ()-> new AppResourceNotFoundException("Person NOT found")
        );
        PersonSmallDto personDto = modelMapper.map(foundPersonById, PersonSmallDto.class);
        return personDto;
    }

    @Override
    public List<PersonSmallDto> findAll() {
        List<Person> allPerson = personDAO.findAll();
        List<PersonSmallDto> allPersonDto = modelMapper.map(allPerson, new TypeToken<List<PersonSmallDto>>(){}.getType());
        return allPersonDto;
    }

    @Override
    public List<PersonSmallDto> findIdlePeople() {
        List<Person> idlePerson = personDAO.findIdlePeople();
        List<PersonSmallDto> allPersonDto = modelMapper.map(idlePerson, new TypeToken<List<PersonSmallDto>>(){}.getType());
        return allPersonDto;
    }

    @Override
    public PersonSmallDto update(Integer personId, PersonForm personForm) {
        if(personId == null) throw new IllegalArgumentException("person Id is null");
        if(personForm == null) throw new IllegalArgumentException("personForm is null");
        if (!personDAO.existsById(personId)) throw new AppResourceNotFoundException("There is no person with that personId in the database");

        Person person = personDAO.findById(personId).get();
        if ((person.getPersonId().equals(personId))){
            person.setFirstName(personForm.getFirstName());
            person.setLastName(personForm.getLastName());
            person.setBirthDate(personForm.getBirthDate());
            person = personDAO.save(person);
        }
        PersonSmallDto personDto = modelMapper.map(person, PersonSmallDto.class);
        return personDto;
    }

    @Override
    public Boolean delete(Integer personId) {
        if (personId == null) throw new IllegalArgumentException("Person Id Can NOT be Null");
        if(!personDAO.existsById(personId)) throw new AppResourceNotFoundException("There is no person with that personId in the database");
        personDAO.deleteById(personId);
        return true;
    }

    @Override
    public PersonSmallDto addTodoItem(Integer personId, Integer todoId) {
        return null;
    }

    @Override
    public PersonSmallDto removeTodoItem(Integer personId, Integer todoId) {
        return null;
    }
}
