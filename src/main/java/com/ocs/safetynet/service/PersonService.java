package com.ocs.safetynet.service;

import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.dto.*;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

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
        this.firestationService = firestationService;
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

    public List<FloodStationDto> getFloodDetailsByStations(List<Integer> firestationNumbers) {
        List<FloodStationDto> floodStationDetails = new ArrayList<>();
        List<Person> persons = personDAO.getAllPersons();

        for (Integer firestationNumber : firestationNumbers) {
            List<String> addresses = firestationService.getAddressesByFirestationNumber(firestationNumber);
            for (String address : addresses) {
                for (Person person : persons) {
                    if (person.getAddress().equals(address)) {
                        Medicalrecord medicalrecord = medicalrecordService.getMedicalrecordByPerson(person);
                        int age = medicalrecordService.calculateAge(medicalrecord);

                        FloodStationDto floodStationDto = new FloodStationDto();
                        floodStationDto.setLastName(person.getLastName());
                        floodStationDto.setPhone(person.getPhone());
                        floodStationDto.setAge(age);
                        floodStationDto.setMedications(medicalrecord.getMedications());
                        floodStationDto.setAllergies(medicalrecord.getAllergies());

                        floodStationDetails.add(floodStationDto);
                    }
                }
            }
        }

        return floodStationDetails;
    }

    public List<PersoninfoNameDto> getPersonInfoByName(String firstName, String lastName) {
        List<PersoninfoNameDto> personInfoList = new ArrayList<>();
        List<Person> persons = getAllPersons();

        for (Person person : persons) {
            if (person.getLastName().equals(lastName)) {
                PersoninfoNameDto personInfo = new PersoninfoNameDto();
                personInfo.setFirstName(person.getFirstName());
                personInfo.setLastName(person.getLastName());
                personInfo.setAddress(person.getAddress());
                personInfo.setEmail(person.getEmail());

                Medicalrecord medicalrecord = medicalrecordService.getMedicalrecordByPerson(person);
                int age = medicalrecordService.calculateAge(medicalrecord);
                personInfo.setAge(age);
                personInfo.setMedications(medicalrecord.getMedications());
                personInfo.setAllergies(medicalrecord.getAllergies());

                personInfoList.add(personInfo);
            }
        }

        return personInfoList;
    }

    public List<CommunityemailDto> getEmailsByCity(String city) {
        List<CommunityemailDto> emails = new ArrayList<>();
        List<Person> persons = getAllPersons();

        for (Person person : persons) {
            if (person.getCity().equals(city)) {
                CommunityemailDto emailDto = new CommunityemailDto();
                emailDto.setEmail(person.getEmail());
                emails.add(emailDto);
            }
        }

        return emails;
    }
}

