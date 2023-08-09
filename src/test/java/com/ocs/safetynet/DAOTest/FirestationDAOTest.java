package com.ocs.safetynet.DAOTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import com.ocs.safetynet.dao.FirestationDAO;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.data.Data;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FirestationDAOTest {

    @Autowired
    FirestationDAO firestationDAO;

    @BeforeEach
    public void setUp() {
        Data.getFirestations().clear();
    }

    @Test
    public void testGetAllFirestations() {
        List<Firestation> firestations = List.of(
                new Firestation("1509 Culver St", "3"),
                new Firestation("29 15th St", "2")
        );

        Data.getFirestations().addAll(firestations);
        List<Firestation> result = firestationDAO.getAllFirestations();

        assertEquals(firestations.size(), result.size());
        assertEquals(firestations, result);
    }

    @Test
    public void testAddFirestation() {
        Firestation firestationToAdd = new Firestation("123 Main St", "4");

        firestationDAO.addFirestation(firestationToAdd);

        List<Firestation> firestations = firestationDAO.getAllFirestations();

        assertEquals(1, firestations.size());
        assertEquals(firestationToAdd, firestations.get(0));
    }

    @Test
    public void testUpdateFirestation() {
        int index = 0;

        Firestation firestationToUpdate = new Firestation("UpdatedAddress", "UpdatedStation");
        Data.getFirestations().add(firestationToUpdate);
        firestationDAO.updateFirestation(index, firestationToUpdate);

        List<Firestation> firestations = firestationDAO.getAllFirestations();

        assertEquals(firestationToUpdate, firestations.get(index));
    }

    @Test
    public void testDeleteFirestation() {
        Firestation firestation = new Firestation("123 Main St", "4");

        Data.getFirestations().add(firestation);
        int index = 0;
        firestationDAO.deleteFirestation(index);

        List<Firestation> firestations = firestationDAO.getAllFirestations();

        assertEquals(0, firestations.size());
    }
}
