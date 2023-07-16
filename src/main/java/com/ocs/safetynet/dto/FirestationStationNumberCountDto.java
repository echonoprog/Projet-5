package com.ocs.safetynet.dto;
import java.util.List;
public class FirestationStationNumberCountDto {

    private List<FirestationStationNumberDto> persons;
    private int adultCount;
    private int childCount;

    public List<FirestationStationNumberDto> getPersons() {
        return persons;
    }

    public void setPersons(List<FirestationStationNumberDto> persons) {
        this.persons = persons;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
