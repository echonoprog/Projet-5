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
}
