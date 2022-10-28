package se.lexicon.todo_it_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todo_it_api.dto.PersonSmallDto;
import se.lexicon.todo_it_api.dto.TodoItemSmallDto;
import se.lexicon.todo_it_api.form.PersonForm;
import se.lexicon.todo_it_api.service.PersonService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/todo/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonSmallDto> createPerson(@RequestBody PersonForm personForm){
        PersonSmallDto createNewPerson = personService.create(personForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createNewPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonSmallDto> update(@PathVariable("id") Integer personId, @RequestBody PersonForm personForm){
        PersonSmallDto person = personService.update(personId, personForm);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Integer personId){
        personService.delete(personId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<PersonSmallDto>> findAll(){
        List<PersonSmallDto> allPerson = personService.findAll();
        return ResponseEntity.ok(allPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonSmallDto> findByPersonId(@PathVariable("id") Integer personId){
        PersonSmallDto personById = personService.findById(personId);
        return ResponseEntity.ok(personById);
    }

    /**
    @GetMapping
    public ResponseEntity<Collection<PersonSmallDto>> find(String firstName){

    }

    @GetMapping
    public ResponseEntity<TodoItemSmallDto> getTodoItem(Integer personId){

    }

    @PutMapping
    public ResponseEntity<PersonSmallDto> addTodoItem(Integer , Integer){

    }

    @DeleteMapping
    public ResponseEntity<PersonSmallDto> removeTodoItem(Integer,Integer){

    }
  */
}
