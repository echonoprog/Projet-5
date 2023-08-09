package com.ocs.safetynet.dao;

import com.ocs.safetynet.model.Person;
import com.ocs.safetynet.data.Data;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class PersonDAO {


    public List<Person> getAllPersons() {

        return Data.getPersons();
    }

    public void addPerson(Person person){
        List<Person> personList = getAllPersons();

        personList.add(person);

    }

    public void updatePerson(int index, Person person) {
        List<Person> personList = getAllPersons();


        personList.set(index,person);

    }

    public void deletePerson(int index) {
        List<Person> personList = getAllPersons();
        personList.remove(index);
        }

}
