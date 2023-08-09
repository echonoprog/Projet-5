package com.ocs.safetynet.DAOTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.model.Person;
import com.ocs.safetynet.data.Data;
import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonDAOTest {

    @Autowired
    PersonDAO personDAO;


/**
    @BeforeAll
    public void setUp() {
        personDAO = new PersonDAO();

    }
**/
    @Test
    public void testGetAllPersons() {

        List<Person> persons = List.of(
                new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com")

        );

        Data.getPersons().addAll(persons);
        List<Person> result = personDAO.getAllPersons();

        assertEquals(persons.size(), result.size());
        assertEquals(persons, result);
    }

    @Test
    public void testAddPerson() {

        Data.getPersons().clear();

        Person personToAdd = new Person("Jane", "Doe", "123 Main St", "City", "12345", "555-555-5555", "jane.doe@example.com");

        personDAO.addPerson(personToAdd);


        List<Person> persons = personDAO.getAllPersons();

        assertEquals(1, persons.size());
        assertEquals(personToAdd, persons.get(0));
    }

    @Test
    public void testUpdatePerson() {
        int index = 0;

        Person personToUpdate = new Person("UpdatedFirstName", "UpdatedLastName", "UpdatedAddress", "UpdatedCity", "UpdatedZip", "UpdatedPhone", "updated.email@example.com");
        Data.getPersons().add(personToUpdate);
        personDAO.updatePerson(index, personToUpdate);


        List<Person> persons = personDAO.getAllPersons();

        assertEquals(personToUpdate, persons.get(index));
    }

    @Test
    public void testDeletePerson() {

        Data.getPersons().clear();

        Person person = new Person("John", "Doe", "123 Main St", "City", "12345", "555-555-5555", "john.doe@example.com");

        Data.getPersons().add(person);
        int index = 0;
        personDAO.deletePerson(index);

        List<Person> persons = personDAO.getAllPersons();

        assertEquals(0, persons.size());

    }
}
