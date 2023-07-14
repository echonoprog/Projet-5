package com.ocs.safetynet.service;

import com.ocs.safetynet.dao.FirestationDAO;
import com.ocs.safetynet.model.Firestation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    private List<Firestation> firestations;
    private FirestationDAO firestationDAO;

    public FirestationService(FirestationDAO firestationDAO) {
        this.firestationDAO = firestationDAO;
    }

    public List<Firestation> getAllFirestations() {
        return firestationDAO.getAllFirestations();
    }

    public  Firestation addFirestation(Firestation firestation) {
        firestationDAO.addFirestation(firestation);
        return firestation;
    }
    public Firestation updateFirestation(String address, String station, Firestation firestation) {
        List<Firestation> firestationList = getAllFirestations();
        Firestation firestationToUpdate = findFirestation(address, station);
        int index = firestationList.lastIndexOf(firestationToUpdate);
        if (index != -1) {
            firestationDAO.updateFirestation(index, firestation);
            return firestation;
        }
        return null;
    }


    public void deleteFirestation(String address, String station) {
        List<Firestation> firestationList = getAllFirestations();
        Firestation firestationToDelete = findFirestation(address, station);
        if (firestationToDelete != null) {
            firestationList.remove(firestationToDelete);
        }
    }

    private Firestation findFirestation(String address, String station) {
        List<Firestation> firestationList = getAllFirestations();
        for (Firestation firestation : firestationList) {
            if (firestation.getAddress().equals(address) && firestation.getStation().equals(station)) {
                return firestation;
            }
        }
        return null;
    }
}
