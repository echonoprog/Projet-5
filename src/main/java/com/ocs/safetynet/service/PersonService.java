package com.ocs.safetynet.service;


import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.dto.ChildAlertDto;
import com.ocs.safetynet.dto.FireAddressDto;
import com.ocs.safetynet.dto.FirestationStationNumberDto;
import com.ocs.safetynet.dto.PhoneAlertFirestationDto;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;




@Service
public class PersonService {

    //    @Autowired
//    PersonDAO personDAO; /** correspond aux lignes 19-23
    private List<Person> persons;
    private final PersonDAO personDAO;
    private final MedicalrecordDAO medicalrecordDAO;
    private MedicalrecordService medicalrecordService;
    private final FirestationService firestationService;

    @Autowired
    public PersonService(PersonDAO personDAO, MedicalrecordDAO medicalrecordDAO, MedicalrecordService medicalrecordService, FirestationService firestationService) {
        this.personDAO = personDAO;
        this.medicalrecordDAO = medicalrecordDAO;
        this.medicalrecordService = medicalrecordService;
        this.firestationService = firestationService; // Ajouter cette ligne
    }

    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }


    public Person updatePerson(String firstName, String lastName, Person person) {
        List<Person> personList = getAllPersons(); // On récupére l'ensemble des personnes existantes dans la base
        Person personToUpdate = findPerson(firstName, lastName);
        int index = personList.lastIndexOf(personToUpdate);
        System.out.println(index);
        personDAO.updatePerson(index, person);
        return person;
    }

    private Person findPerson(String firstName, String lastName) {
        List<Person> personList = getAllPersons();
        for (Person person : personList) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                return person;
            }
        }
        return null;
    }

    public void deletePerson(String firstName, String lastName) {
        List<Person> personList = getAllPersons();
        Person personToUpdate = findPerson(firstName, lastName);
        int index = personList.lastIndexOf(personToUpdate);
        personDAO.deletePerson(index);
    }

    public Person addPerson(Person person) {
        personDAO.addPerson(person);
        return person;
    }


    public List<ChildAlertDto> getChildrenAtAddress(String address) {
        List<ChildAlertDto> childrenAtAddress = new ArrayList<>();

        List<Person> persons = personDAO.getAllPersons();

        for (Person person : persons) {
            if (person.getAddress().equals(address)) {
                Medicalrecord medicalrecord = medicalrecordService.getMedicalrecordByPerson(person);
                if (medicalrecord != null) {
                    int age = medicalrecordService.calculateAge(medicalrecord);
                    if (age <= 18) { // Vérifier si l'âge est inférieur ou égal à 18 ans
                        ChildAlertDto child = new ChildAlertDto();
                        child.setFirstName(person.getFirstName());
                        child.setLastName(person.getLastName());
                        child.setAge(age);

                        // Rechercher tous les membres du foyer ayant la même adresse que l'enfant
                        List<String> familyMembers = new ArrayList<>();
                        for (Person familyMember : persons) {
                            if (person.getAddress().equals(familyMember.getAddress())) {
                                familyMembers.add(familyMember.getFirstName() + " " + familyMember.getLastName());
                            }
                        }
                        child.setFamilyMembers(familyMembers);

                        childrenAtAddress.add(child);
                    }
                }
            }
        }

        return childrenAtAddress;
    }

    public List<PhoneAlertFirestationDto> getPhoneNumbersByFirestation(int firestationNumber) {
        List<PhoneAlertFirestationDto> phoneNumbers = new ArrayList<>();

        List<Person> residents = personDAO.getAllPersons();
        List<Firestation> firestations = firestationService.getAllFirestations();

        for (Firestation firestation : firestations) {
            if (firestation.getStation().equals(String.valueOf(firestationNumber))) {
                for (Person resident : residents) {
                    if (resident.getAddress().equals(firestation.getAddress())) {
                        PhoneAlertFirestationDto phoneAlertDto = new PhoneAlertFirestationDto();
                        phoneAlertDto.setPhone(resident.getPhone());
                        phoneNumbers.add(phoneAlertDto);
                    }
                }
            }
        }

        return phoneNumbers;
    }


    public List<FireAddressDto> getFireAddressDetails(String address) {
        List<FireAddressDto> fireAddressDetails = new ArrayList<>();
        List<Person> persons = personDAO.getAllPersons();

        for (Person person : persons) {
            if (person.getAddress().equals(address)) {
                FireAddressDto fireAddressDto = new FireAddressDto();
                fireAddressDto.setFirstName(person.getFirstName());
                fireAddressDto.setLastName(person.getLastName());
                fireAddressDto.setPhone(person.getPhone());
                Medicalrecord medicalrecord = medicalrecordService.getMedicalrecordByPerson(person);
                int age = medicalrecordService.calculateAge(medicalrecord);
                fireAddressDto.setAge(age);
                fireAddressDto.setMedications(medicalrecord.getMedications());
                fireAddressDto.setAllergies(medicalrecord.getAllergies());


                String firestationNumber = firestationService.getFirestationNumberByAddress(address);
                fireAddressDto.setFirestationNumber(firestationNumber);

                fireAddressDetails.add(fireAddressDto);
            }
        }

        return fireAddressDetails;
    }
}

