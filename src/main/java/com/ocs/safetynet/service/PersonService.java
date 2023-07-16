package com.ocs.safetynet.service;


import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.dto.ChildAlertDto;
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

    @Autowired
    public PersonService(PersonDAO personDAO, MedicalrecordDAO medicalrecordDAO, MedicalrecordService medicalrecordService) {
        this.personDAO = personDAO;
        this.medicalrecordDAO = medicalrecordDAO;
        this.medicalrecordService = medicalrecordService; // Ajouter cette ligne
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

}