package com.ocs.safetynet.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import com.ocs.safetynet.service.MedicalrecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class MedicalrecordServiceTest {

    private MedicalrecordService medicalrecordService;
    private MedicalrecordDAO medicalrecordDAO;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        medicalrecordDAO = mock(MedicalrecordDAO.class);
        medicalrecordService = new MedicalrecordService(medicalrecordDAO);
    }

    @Test
    public void testGetAllMedicalrecords() {
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        when(medicalrecordDAO.getAllMedicalrecords()).thenReturn(medicalrecords);

        List<Medicalrecord> result = medicalrecordService.getAllMedicalrecords();

        assertEquals(medicalrecords, result);
    }

    @Test
    public void testAddMedicalrecord() {
        Medicalrecord medicalrecordToAdd = new Medicalrecord();


        medicalrecordService.addMedicalrecord(medicalrecordToAdd);

        verify(medicalrecordDAO).addMedicalrecord(medicalrecordToAdd);
    }


    @Test
    public void testUpdateMedicalrecord() {
        List<Medicalrecord> medicalrecordList = new ArrayList<>();
        Medicalrecord medicalrecordToUpdate = new Medicalrecord();
        medicalrecordToUpdate.setFirstName("John");
        medicalrecordToUpdate.setLastName("Boyd");
        medicalrecordList.add(medicalrecordToUpdate);
        when(medicalrecordDAO.getAllMedicalrecords()).thenReturn(medicalrecordList);

        Medicalrecord updatedMedicalrecord = new Medicalrecord();
        Medicalrecord result = medicalrecordService.updateMedicalrecord("John", "Boyd", updatedMedicalrecord);

        assertEquals(updatedMedicalrecord, result);
    }

    @Test
    public void testDeleteMedicalRecord() {
        String firstName = "John";
        String lastName = "Doe";

        List<Medicalrecord> medicalrecords = new ArrayList<>();
        medicalrecords.add(new Medicalrecord("John", "Doe"));
        medicalrecords.add(new Medicalrecord("Jane", "Smith"));

        when(medicalrecordDAO.getAllMedicalrecords()).thenReturn(medicalrecords);

        medicalrecordService.deleteMedicalRecord(firstName, lastName);

        assertFalse(medicalrecords.contains(new Medicalrecord(firstName, lastName)));
    }



    @Test
    public void testCalculateAge() {
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        Medicalrecord medicalrecord = new Medicalrecord();
        medicalrecord.setFirstName("John");
        medicalrecord.setLastName("Boyd");
        medicalrecord.setBirthdate("03/06/1984");
        medicalrecords.add(medicalrecord);

        when(medicalrecordDAO.getAllMedicalrecords()).thenReturn(medicalrecords);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        int age = medicalrecordService.calculateAge(medicalrecordService.getMedicalrecordByPerson(person));

        assertEquals(39, age);
    }

    @Test
    public void testGetMedicalrecordByPerson() {
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        Medicalrecord medicalrecord = new Medicalrecord();
        medicalrecord.setFirstName("John");
        medicalrecord.setLastName("Boyd");
        medicalrecords.add(medicalrecord);

        when(medicalrecordDAO.getAllMedicalrecords()).thenReturn(medicalrecords);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        Medicalrecord result = medicalrecordService.getMedicalrecordByPerson(person);

        assertEquals(medicalrecord, result);
    }

}
