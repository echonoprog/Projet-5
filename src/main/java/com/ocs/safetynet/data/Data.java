package com.ocs.safetynet.data;

import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Data {

    private static List<Person> persons = new ArrayList<Person>();
    private static List<Firestation> firestations =  new ArrayList<Firestation>();
    private static List<Medicalrecord> medicalrecords = new ArrayList<Medicalrecord>();

    public static List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        Data.persons = persons;
    }

    public static List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        Data.firestations = firestations;
    }

    public static List<Medicalrecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
        Data.medicalrecords = medicalrecords;
    }

    @Override
    public String toString() {
        return "Data{ " +
                "persons=" + persons +
                ", firestations=" + firestations +
                ", medicalrecords=" + medicalrecords +
                "}";

    }

}