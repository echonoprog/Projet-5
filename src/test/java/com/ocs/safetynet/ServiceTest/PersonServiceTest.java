package com.ocs.safetynet.ServiceTest;

import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.dto.ChildAlertDto;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import com.ocs.safetynet.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    @Mock
    private PersonDAO personDAO;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPersons() {
        List<Person> personnes = new ArrayList<>();
        personnes.add(new Person("John", "Doe", "123 Main St", "City", "12345", "555-1234", "john@example.com"));
        personnes.add(new Person("Jane", "Doe", "123 Main St", "City", "12345", "555-5678", "jane@example.com"));

        when(personDAO.getAllPersons()).thenReturn(personnes);

        List<Person> resultat = personService.getAllPersons();

        assertEquals(2, resultat.size());
    }



   @Test
   void testUpdatePerson() {

       String firstName = "John";
       String lastName = "Boyd";


       Person updatedPerson = new Person();
       updatedPerson.setFirstName(firstName);
       updatedPerson.setLastName(lastName);

       Person result = personService.updatePerson(firstName, lastName, updatedPerson);

       assertNotNull(result);
       assertEquals(firstName, result.getFirstName());
       assertEquals(lastName, result.getLastName());
   }
    @Test
    public void testDeletePerson() {
        String firstName = "John";
        String lastName = "Doe";

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe"));
        persons.add(new Person("Jane", "Smith"));

        when(personDAO.getAllPersons()).thenReturn(persons);

        personService.deletePerson(firstName, lastName);

        assertFalse(persons.contains(new Person(firstName, lastName)));
    }

    @Test
    public void testAddPerson() {

        Person personToAdd = new Person();
        personService.addPerson(personToAdd);
        verify(personDAO).addPerson(personToAdd);
    }



}
