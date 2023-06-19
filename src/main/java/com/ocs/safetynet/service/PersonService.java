package com.ocs.safetynet.service;


import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private List<Person> persons;
    private PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }



    public List<Person> getAllPersons(){
        return personDAO.getAllPersons();
    }

    


    public Person updatePerson(Person person) {
        return person;
    }

    public void deletePerson(String firstName, String lastName) {

    }

    public Person addPerson(Person person) {
        return null;
    }
}
