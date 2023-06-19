package com.ocs.safetynet.service;

import com.ocs.safetynet.dao.FirestationDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    private List<Firestation> firestations;
    private FirestationDAO firestationDAO;

    public FirestationService(FirestationDAO firestationDAO) {
        this.firestationDAO = firestationDAO;
    }

    public  Firestation addFirestation(Firestation firestation) {
        return null;
    }

    public  Firestation updateFirestation(Firestation firestation) {
        return null;
    }

   
    public List<Firestation> getAllFirestations() {
        return firestationDAO.getAllFirestations();
    }

    public void deleteFirestation(String address) {
    }
}
