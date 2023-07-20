package com.ocs.safetynet.controller;


import com.ocs.safetynet.dto.*;
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
    public ResponseEntity<List<PhoneAlertFirestationDto>> getPhoneNumbersByFirestation(@PathVariable("firestation") int firestationNumber) {
        List<PhoneAlertFirestationDto> phoneNumbers = personService.getPhoneNumbersByFirestation(firestationNumber);
        if (phoneNumbers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(phoneNumbers);
        }
    }

    @GetMapping("/fire/{address}")
    public ResponseEntity<List<FireAddressDto>> getFireAddressDetails(@PathVariable("address") String address) {
        List<FireAddressDto> fireAddressDetails = personService.getFireAddressDetails(address);
        if (fireAddressDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(fireAddressDetails);
        }
    }

    @GetMapping("/flood/{stations}")
    public ResponseEntity<List<FloodStationDto>> getFloodDetailsByStations(@PathVariable("stations") List<Integer> firestationNumbers) {
        List<FloodStationDto> floodStationDetails = personService.getFloodDetailsByStations(firestationNumbers);
        if (floodStationDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(floodStationDetails);
        }
    }

    @GetMapping("/personInfo/{firstName}&{lastName}")
    public ResponseEntity<List<PersoninfoNameDto>> getPersonInfo(@PathVariable String firstName, @PathVariable String lastName) {
        List<PersoninfoNameDto> personInfoList = personService.getPersonInfoByName(firstName, lastName);
        if (personInfoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(personInfoList);
        }
    }

    @GetMapping("/communityEmail/{city}")
    public ResponseEntity<List<CommunityemailDto>> getEmailsByCity(@PathVariable String city) {
        List<CommunityemailDto> emails = personService.getEmailsByCity(city);
        if (emails.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(emails);
        }
    }
}
