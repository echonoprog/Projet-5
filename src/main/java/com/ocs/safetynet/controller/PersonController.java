package com.ocs.safetynet.controller;


import com.ocs.safetynet.model.Person;
import com.ocs.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController

public class PersonController {


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
           return personService.getAllPersons();

    }


    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person addedPerson = personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPerson);
    }

    @PutMapping("/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/person/{firstName}/{lastName}")
    public ResponseEntity<Void> deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        personService.deletePerson(firstName, lastName);
        return ResponseEntity.noContent().build();
    }

}