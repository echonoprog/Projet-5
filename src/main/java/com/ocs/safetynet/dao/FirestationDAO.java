package com.ocs.safetynet.dao;

import com.ocs.safetynet.data.Data;
import com.ocs.safetynet.model.Firestation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirestationDAO {

    public List<Firestation> getAllFirestations() {
        return Data.getFirestations();
    }

    public void addFirestation(Firestation firestation) {
        List<Firestation> firestationList = getAllFirestations();
        firestationList.add(firestation);
    }

    public void updateFirestation(int index, Firestation firestation) {
        List<Firestation> firestationList = getAllFirestations();
        firestationList.set(index, firestation);
    }

    public void deleteFirestation(int index) {
        List<Firestation> firestationList = getAllFirestations();
        firestationList.remove(index);
    }


}
