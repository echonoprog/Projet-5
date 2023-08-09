package com.ocs.safetynet.ServiceTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.ocs.safetynet.dao.FirestationDAO;
import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.dto.FirestationStationNumberCountDto;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import com.ocs.safetynet.service.FirestationService;
import com.ocs.safetynet.service.MedicalrecordService;
import org.junit.Before;
import org.junit.Test;

public class FirestationServiceTest {

    private FirestationService firestationService;
    private MedicalrecordService medicalrecordService;
    private FirestationDAO firestationDAO;
    private PersonDAO personDAO;

    private MedicalrecordDAO medicalrecordDAO;

    @Before
    public void setUp() {
        firestationDAO = mock(FirestationDAO.class);
        personDAO = mock(PersonDAO.class);
        medicalrecordService = mock(MedicalrecordService.class);
        firestationService = new FirestationService(firestationDAO, personDAO, medicalrecordService);

    }


    @Test
    public void testGetAllFirestations() {
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));
        firestations.add(new Firestation("29 15th St", "2"));

        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        List<Firestation> result = firestationService.getAllFirestations();

        assertEquals(firestations, result);
    }

    @Test
    public void testAddFirestation() {
        Firestation firestationToAdd = new Firestation("New Address", "5");

        List<Firestation> firestations = new ArrayList<>();
        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        Firestation result = firestationService.addFirestation(firestationToAdd);
        firestations.add(firestationToAdd);

        assertTrue(firestations.contains(firestationToAdd));
        assertEquals(firestationToAdd, result);
    }

    @Test
    public void testUpdateFirestation() {
        String address = "1509 Culver St";
        String station = "3";
        Firestation firestationToUpdate = new Firestation("Updated Address", "10");

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));
        firestations.add(new Firestation("29 15th St", "2"));


        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        Firestation result = firestationService.updateFirestation(address, station, firestationToUpdate);

        assertNotNull(result);
        assertEquals(firestationToUpdate, result);
    }

    @Test
    public void testDeleteFirestation() {
        String address = "1509 Culver St";
        String station = "3";

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));
        firestations.add(new Firestation("29 15th St", "2"));

        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        firestationService.deleteFirestation(address, station);

        assertFalse(firestations.contains(new Firestation(address, station)));
    }

    @Test
    public void testFindFirestation() {
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));
        firestations.add(new Firestation("29 15th St", "2"));

        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        Firestation result = firestationService.findFirestation("1509 Culver St", "3");

        assertEquals(firestations.get(0), result);
    }

    @Test
    public void testGetFirestationCoverageByStationNumber() {


        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe", "1509 Culver St", "Culver", "12345", "841-874-6512", "john.doe@email.com")
        );

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));

        when(personDAO.getAllPersons()).thenReturn(persons);
        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        when(medicalrecordService.getMedicalrecordByPerson(any())).thenReturn(new Medicalrecord());
        when(medicalrecordService.calculateAge(any())).thenReturn(25);

        FirestationStationNumberCountDto result = firestationService.getFirestationCoverageByStationNumber("3");

        assertNotNull(result);
        assertEquals(1, result.getPersons().size());
    }

    @Test
    public void testGetFirestationNumberByAddress() {
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));
        firestations.add(new Firestation("29 15th St", "2"));

        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        String result = firestationService.getFirestationNumberByAddress("1509 Culver St");

        assertEquals("3", result);
    }

    @Test
    public void testGetAddressesByFirestationNumber() {
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(new Firestation("1509 Culver St", "3"));
        firestations.add(new Firestation("29 15th St", "3"));
        firestations.add(new Firestation("123 Main St", "2"));

        when(firestationDAO.getAllFirestations()).thenReturn(firestations);

        List<String> result = firestationService.getAddressesByFirestationNumber(3);

        assertEquals(2, result.size());
        assertTrue(result.contains("1509 Culver St"));
        assertTrue(result.contains("29 15th St"));
    }


}
