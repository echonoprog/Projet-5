package com.ocs.safetynet.DAOTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.data.Data;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicalrecordDAOTest {

    @Autowired
    MedicalrecordDAO medicalrecordDAO;

    @BeforeEach
    public void setUp() {
        // Initialiser les données ou effectuer d'autres opérations nécessaires avant chaque test.
    }

    @Test
    public void testGetAllMedicalrecords() {
        Data.getMedicalrecords().clear();
        Medicalrecord medicalrecord = new Medicalrecord();
        medicalrecord.setFirstName("John");
        medicalrecord.setLastName("Boyd");
        medicalrecord.setBirthdate("03/06/1984");
        medicalrecord.setMedications(List.of("aznol:350mg", "hydrapermazol:100mg"));
        medicalrecord.setAllergies(List.of("nillacilan"));

        Data.getMedicalrecords().add(medicalrecord);
        List<Medicalrecord> result = medicalrecordDAO.getAllMedicalrecords();

        assertEquals(1, result.size());
        assertEquals(medicalrecord, result.get(0));
    }


    @Test
    public void testAddMedicalrecord() {

        Data.getMedicalrecords().clear();

        Medicalrecord medicalrecordToAdd = new Medicalrecord("Jane", "Doe", "01/01/1990", List.of("medication1", "medication2"), List.of("allergy1", "allergy2"));

        medicalrecordDAO.addMedicalrecord(medicalrecordToAdd);

        List<Medicalrecord> medicalrecords = medicalrecordDAO.getAllMedicalrecords();

        assertEquals(1, medicalrecords.size());
        assertEquals(medicalrecordToAdd, medicalrecords.get(0));
    }

    @Test
    public void testUpdateMedicalrecord() {
        int index = 0;

        Medicalrecord medicalrecordToUpdate = new Medicalrecord("UpdatedFirstName", "UpdatedLastName", "03/06/2000", List.of("updatedMedication"), List.of("updatedAllergy"));
        Data.getMedicalrecords().add(medicalrecordToUpdate);
        medicalrecordDAO.updateMedicalrecord(index, medicalrecordToUpdate);

        List<Medicalrecord> medicalrecords = medicalrecordDAO.getAllMedicalrecords();

        assertEquals(medicalrecordToUpdate, medicalrecords.get(index));
    }

    @Test
    public void testDeleteMedicalrecord() {

        Data.getMedicalrecords().clear();

        Medicalrecord medicalrecord = new Medicalrecord("John", "Doe", "01/01/1980", List.of("medication"), List.of("allergy"));

        Data.getMedicalrecords().add(medicalrecord);
        int index = 0;
        medicalrecordDAO.deleteMedicalrecord(index);

        List<Medicalrecord> medicalrecords = medicalrecordDAO.getAllMedicalrecords();

        assertEquals(0, medicalrecords.size());
    }
}
