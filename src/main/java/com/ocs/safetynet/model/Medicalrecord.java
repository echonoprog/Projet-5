package com.ocs.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Medicalrecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public Medicalrecord(){

    }
    public Medicalrecord(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Medicalrecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getFirstName (){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getLastName (){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getBirthdate (){
        return birthdate;
    }
    public void setBirthdate(String birthdate){
        this.birthdate=birthdate;
    }
    public List<String> getMedications (){
        return medications;
    }
    public void setMedications(List<String> medications){
        this.medications = medications;
    }
    public List<String> getAllergies (){
        return allergies;
    }
    public void setAllergies(List<String> allergies){
        this.allergies = allergies;
    }

    @Override
    public String toString(){
        return "medicalrecords {" +
                "firstName =" + firstName +
                "lastName =" + lastName +
                "firstName =" + firstName +
                "birthdate =" + birthdate +
                "medications =" + medications +
                "allergies =" + allergies +
                "}";
    }
}
