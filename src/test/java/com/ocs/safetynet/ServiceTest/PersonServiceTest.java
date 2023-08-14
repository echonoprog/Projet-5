package com.ocs.safetynet.ServiceTest;

import com.ocs.safetynet.dao.FirestationDAO;
import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.data.Data;
import com.ocs.safetynet.dto.ChildAlertDto;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;

import com.ocs.safetynet.service.FirestationService;
import com.ocs.safetynet.service.MedicalrecordService;
import com.ocs.safetynet.service.PersonService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class PersonServiceTest {

    private MedicalrecordService medicalrecordService;
    private MedicalrecordDAO medicalrecordDAO;
    private PersonService personService;
    private PersonDAO personDAO;
    private Data data;

    private FirestationService firestationService;
    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        medicalrecordDAO = mock(MedicalrecordDAO.class);
        medicalrecordService = new MedicalrecordService(medicalrecordDAO);


        personDAO = mock(PersonDAO.class);
        personService = new PersonService(personDAO, medicalrecordDAO, medicalrecordService, firestationService);


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

    @Test
    public void testGetChildrenAtAddress() {

        List<Person> personsList = new ArrayList<>();
        List<Medicalrecord> medicalRecordsList = new ArrayList<>();

        Person child1 = new Person();
        child1.setFirstName("John");
        child1.setLastName("Boyd");
        child1.setAddress("1509 Culver St");
        child1.setCity("Culver");
        child1.setZip("97451");
        child1.setPhone("841-874-6512");
        child1.setEmail("jaboyd@email.com");

        Person familyMember = new Person();
        familyMember.setFirstName("Jacob");
        familyMember.setLastName("Boyd");
        familyMember.setAddress("1509 Culver St");
        familyMember.setCity("Culver");
        familyMember.setZip("97451");
        familyMember.setPhone("841-874-6513");
        familyMember.setEmail("drk@email.com");

        personsList.add(child1);
        personsList.add(familyMember);

        when(personDAO.getAllPersons()).thenReturn(personsList);
        when(medicalrecordDAO.getAllMedicalrecords()).thenReturn(medicalRecordsList);


        List<ChildAlertDto> result = personService.getChildrenAtAddress("1509 Culver St");

        assertEquals(1, result.size());

    }

}
