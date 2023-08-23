package com.ocs.safetynet.ControllerTest;

import com.ocs.safetynet.controller.PersonController;
import com.ocs.safetynet.dto.*;
import com.ocs.safetynet.model.Person;
import com.ocs.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    public MockMvc mvc;

    @MockBean
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void testGetAllPersons() throws Exception {
        List<Person> persons = new ArrayList<>();

        when(personService.getAllPersons()).thenReturn(persons);

        mvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAddPerson() throws Exception {
        Person personToAdd = new Person();

        when(personService.addPerson(any(Person.class))).thenReturn(personToAdd);

        mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        Person updatedPerson = new Person();

        when(personService.updatePerson(eq(firstName), eq(lastName), any(Person.class))).thenReturn(updatedPerson);

        mvc.perform(put("/person/{firstName}&{lastName}", firstName, lastName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testDeletePerson() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        mvc.perform(delete("/person/{firstName}&{lastName}", firstName, lastName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetChildAlert() throws Exception {
        String address = "123 Main St";
        List<ChildAlertDto> childrenAtAddress = new ArrayList<>();

        when(personService.getChildrenAtAddress(eq(address))).thenReturn(childrenAtAddress);

        mvc.perform(get("/childAlert/{address}", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void testGetPhoneNumbersByFirestation() throws Exception {
        int firestationNumber = 1;
        List<PhoneAlertFirestationDto> phoneNumbers = new ArrayList<>();

        when(personService.getPhoneNumbersByFirestation(eq(firestationNumber))).thenReturn(phoneNumbers);

        mvc.perform(get("/phoneAlert/{firestation}", firestationNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void testGetFireAddressDetails() throws Exception {
        String address = "123 Main St";
        List<FireAddressDto> fireAddressDetails = new ArrayList<>();

        when(personService.getFireAddressDetails(eq(address))).thenReturn(fireAddressDetails);

        mvc.perform(get("/fire/{address}", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void testGetFloodDetailsByStations() throws Exception {
        List<Integer> firestationNumbers = new ArrayList<>();
        List<FloodStationDto> floodStationDetails = new ArrayList<>();

        when(personService.getFloodDetailsByStations(eq(firestationNumbers))).thenReturn(floodStationDetails);

        mvc.perform(get("/flood/{stations}", "1,2,3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void testGetPersonInfo() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        List<PersoninfoNameDto> personInfoList = new ArrayList<>();

        when(personService.getPersonInfoByName(eq(firstName), eq(lastName))).thenReturn(personInfoList);

        mvc.perform(get("/personInfo/{firstName}&{lastName}", firstName, lastName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void testGetEmailsByCity() throws Exception {
        String city = "New York";
        List<CommunityemailDto> emails = new ArrayList<>();

        when(personService.getEmailsByCity(eq(city))).thenReturn(emails);

        mvc.perform(get("/communityEmail/{city}", city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }
}
