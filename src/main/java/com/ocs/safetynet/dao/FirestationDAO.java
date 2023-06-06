package com.ocs.safetynet.dao;

import com.ocs.safetynet.data.Data;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirestationDAO {


    public static List<Firestation> getAllFirestations() {

        return Data.getFirestations();
    }
}
