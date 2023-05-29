package com.ocs.safetynet.data;

import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Data {

    public  static List<Person> persons;
    public  static List<Firestation> firestations;
    public  static List<Medicalrecord> medicalrecords;

    public static List<Person> getPersons() {
        return persons;
    }

    public static void setPersons(List<Person> persons) {
        Data.persons = persons;
    }

    public static List<Firestation> getFirestations() {
        return firestations;
    }

    public static void setFirestations(List<Firestation> firestations) {
        Data.firestations = firestations;
    }

    public static List<Medicalrecord> getMedicalrecords() {
        return medicalrecords;
    }

    public static void setMedicalrecords(List<Medicalrecord> medicalrecords) {
        Data.medicalrecords = medicalrecords;
    }
}