package com.ocs.safetynet.service;


import com.ocs.safetynet.dao.PersonDAO;
import com.ocs.safetynet.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonService {

//    @Autowired
//    PersonDAO personDAO; /** correspond aux lignes 19-23
    private List<Person> persons;
    private PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> getAllPersons(){
        return personDAO.getAllPersons();
    }


    public Person updatePerson(String firstName, String lastName, Person person) {
        List <Person> personList = getAllPersons(); // On récupére l'ensemble des personnes existantes dans la base
        Person personToUpdate = findPerson(firstName,lastName);
        int index =  personList.lastIndexOf(personToUpdate);
        System.out.println(index);
        personDAO.updatePerson(index,person);
        return person;
    }

    private Person findPerson(String firstName, String lastName) {
        List <Person> personList = getAllPersons();
        for (Person person : personList){
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)){
                return person;
            }
        }
        return null;
    }

    public void deletePerson(String firstName, String lastName) {
        List <Person> personList = getAllPersons();
        Person personToUpdate = findPerson(firstName,lastName);
        int index =  personList.lastIndexOf(personToUpdate);
        personDAO.deletePerson(index);
    }

    public Person addPerson(Person person) {
        personDAO.addPerson(person);
        return person;
    }


}
