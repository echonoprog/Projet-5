package com.ocs.safetynet.controller;


import com.ocs.safetynet.dto.ChildAlertDto;
import com.ocs.safetynet.dto.PhoneAlertFirestationDto;
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

    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();

    }


    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person addedPerson = personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPerson);
    }

    @PutMapping("/person/{firstName}&{lastName}")
    public ResponseEntity<Person> updatePerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(firstName, lastName, person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/person/{firstName}&{lastName}")
    public ResponseEntity<Void> deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        personService.deletePerson(firstName, lastName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/childAlert/{address}")
    public ResponseEntity<List<ChildAlertDto>> getChildAlert(@PathVariable String address) {
        List<ChildAlertDto> childrenAtAddress = personService.getChildrenAtAddress(address);
        if (childrenAtAddress.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(childrenAtAddress);
        }
    }

    @GetMapping("/phoneAlert/{firestation}")
    public ResponseEntity<List<String>> getPhoneNumbersByFirestation(@PathVariable("firestation") int firestationNumber) {
        List<String> phoneNumbers = personService.getPhoneNumbersByFirestation(firestationNumber);
        if (phoneNumbers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(phoneNumbers);
        }
    }
}