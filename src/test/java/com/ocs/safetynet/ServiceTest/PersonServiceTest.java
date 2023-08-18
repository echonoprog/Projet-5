package com.ocs.safetynet.ServiceTest;

import com.ocs.safetynet.dao.FirestationDAO;
import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.data.Data;
import com.ocs.safetynet.dto.ChildAlertDto;
import com.ocs.safetynet.dto.FireAddressDto;
import com.ocs.safetynet.dto.PhoneAlertFirestationDto;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;

import com.ocs.safetynet.service.FirestationService;
import com.ocs.safetynet.service.MedicalrecordService;
import com.ocs.safetynet.service.PersonService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private MedicalrecordService medicalrecordService;
    @Mock
    private MedicalrecordDAO medicalrecordDAO;
    @Mock
    private PersonDAO personDAO;
    @Mock
    private Data data;
    @Mock
    private FirestationService firestationService;
    @Mock
    private FirestationDAO firestationDAO;



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
        child1.setFirstName("Tenley");
        child1.setLastName("Boyd");
        child1.setAddress("1509 Culver St");
        child1.setCity("Culver");
        child1.setZip("97451");
        child1.setPhone("841-874-6512");
        child1.setEmail("tenz@email.com");

        Person child2 = new Person();
        child2.setFirstName("Roger");
        child2.setLastName("Boyd");
        child2.setAddress("1509 Culver St");
        child2.setCity("Culver");
        child2.setZip("97451");
        child2.setPhone("841-874-6512");
        child2.setEmail("jaboyd@email.com");

        Medicalrecord medicalrecord1 = new Medicalrecord();
        medicalrecord1.setFirstName("Tenley");
        medicalrecord1.setLastName("Boyd");
        medicalrecord1.setBirthdate("02/18/2012");

        Medicalrecord medicalrecord2 = new Medicalrecord();
        medicalrecord2.setFirstName("Roger");
        medicalrecord2.setLastName("Boyd");
        medicalrecord2.setBirthdate("09/06/2017");

        personsList.add(child1);
        personsList.add(child2);

        medicalRecordsList.add(medicalrecord1);
        medicalRecordsList.add(medicalrecord2);

        when(personDAO.getAllPersons()).thenReturn(personsList);
        when(medicalrecordService.getAllMedicalrecords()).thenReturn(medicalRecordsList);


        List<ChildAlertDto> result = personService.getChildrenAtAddress("1509 Culver St");

        System.out.println("Number of children found: " + result.size());

        assertEquals(2, result.size());
    }

    @Test
    public void testGetPhoneNumbersByFirestation() {
        int firestationNumber = 3;

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setPhone("841-874-6512");
        person.setAddress("1509 Culver St");
        persons.add(person);

        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");
        firestations.add(firestation);

        when(personDAO.getAllPersons()).thenReturn(persons);
        when(firestationService.getAllFirestations()).thenReturn(firestations);

        List<PhoneAlertFirestationDto> phoneNumbers = personService.getPhoneNumbersByFirestation(firestationNumber);

        assertEquals(1, phoneNumbers.size());
        assertEquals("841-874-6512", phoneNumbers.get(0).getPhone());
    }

    @Test
    public void testGetFireAddressDetails() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "john@example.com"));

        List<Medicalrecord> medicalRecords = new ArrayList<>();
        Medicalrecord medicalrecord = new Medicalrecord();
        medicalRecords.add(medicalrecord);

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));

        when(medicalrecordService.getMedicalrecordByPerson(any())).thenReturn(medicalrecord);
        when(firestationService.getFirestationNumberByAddress(any())).thenReturn("3");

        when(personDAO.getAllPersons()).thenReturn(persons);
        when(medicalrecordService.getAllMedicalrecords()).thenReturn(medicalRecords);
        when(firestationService.getAllFirestations()).thenReturn(firestations);


        when(medicalrecordService.calculateAge(any())).thenReturn(30);

        List<FireAddressDto> result = personService.getFireAddressDetails("1509 Culver St");

        assertEquals(1, result.size());
    }

}
