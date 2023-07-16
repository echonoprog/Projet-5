package com.ocs.safetynet.service;

import com.ocs.safetynet.dao.FirestationDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.dto.FirestationStationNumberCountDto;
import com.ocs.safetynet.dto.FirestationStationNumberDto;
import com.ocs.safetynet.dto.PersonDto;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationService {

    private List<Firestation> firestations;
    private PersonService personService;
    private FirestationDAO firestationDAO;
    private PersonDAO personDAO;
    private MedicalrecordService medicalrecordService;


    @Autowired
    public FirestationService(FirestationDAO firestationDAO, PersonDAO personDAO, MedicalrecordService medicalrecordService) {
        this.firestationDAO = firestationDAO;
        this.personDAO = personDAO;
        this.medicalrecordService = medicalrecordService;
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


    public FirestationStationNumberCountDto getFirestationCoverageByStationNumber(String stationNumber) {
        List<Person> persons = personDAO.getAllPersons();
        List<Firestation> firestations = firestationDAO.getAllFirestations();

        List<FirestationStationNumberDto> personsCoveredByFirestation = new ArrayList<>();
        int adultCount = 0;
        int childCount = 0;

        for (Firestation firestation : firestations) {
            if (firestation.getStation().equals(stationNumber)) {
                for (Person person : persons) {
                    if (person.getAddress().equals(firestation.getAddress())) {
                        FirestationStationNumberDto personCoveredByFirestationDto = new FirestationStationNumberDto();
                        personCoveredByFirestationDto.setFirstName(person.getFirstName());
                        personCoveredByFirestationDto.setLastName(person.getLastName());
                        personCoveredByFirestationDto.setAddress(person.getAddress());
                        personCoveredByFirestationDto.setPhone(person.getPhone());

                        personsCoveredByFirestation.add(personCoveredByFirestationDto);

                        Medicalrecord medicalrecord = medicalrecordService.getMedicalrecordByPerson(person);
                        int age = medicalrecordService.calculateAge(medicalrecord);
                        if (age <= 18) {
                            childCount++;
                        } else {
                            adultCount++;
                        }
                    }
                }
            }
        }

        FirestationStationNumberCountDto firestationCoverageDto = new FirestationStationNumberCountDto();
        firestationCoverageDto.setPersons(personsCoveredByFirestation);
        firestationCoverageDto.setAdultCount(adultCount);
        firestationCoverageDto.setChildCount(childCount);

        return firestationCoverageDto;
    }
}





