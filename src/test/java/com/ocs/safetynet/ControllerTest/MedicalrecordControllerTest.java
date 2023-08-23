package com.ocs.safetynet.ControllerTest;

import com.ocs.safetynet.controller.MedicalrecordController;
import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.service.MedicalrecordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalrecordControllerTest {

    @Autowired
    public MockMvc mvc;

    @MockBean
    private MedicalrecordService medicalrecordService;

    @InjectMocks
    private MedicalrecordController medicalrecordController;

    @Test
    public void testGetAllMedicalrecords() throws Exception {
        List<Medicalrecord> medicalrecords = new ArrayList<>();

        when(medicalrecordService.getAllMedicalrecords()).thenReturn(medicalrecords);

        mvc.perform(get("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAddMedicalrecord() throws Exception {
        Medicalrecord medicalrecordToAdd = new Medicalrecord();

        when(medicalrecordService.addMedicalrecord(any(Medicalrecord.class))).thenReturn(medicalrecordToAdd);

        mvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateMedicalrecord() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        Medicalrecord updatedMedicalrecord = new Medicalrecord();

        when(medicalrecordService.updateMedicalrecord(eq(firstName), eq(lastName), any(Medicalrecord.class))).thenReturn(updatedMedicalrecord);

        mvc.perform(put("/medicalRecord/{firstName}&{lastName}", firstName, lastName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testDeleteMedicalrecord() throws Exception {
        String firstName = "John";
        String lastName = "Doe";

        mvc.perform(delete("/medicalRecord/{firstName}&{lastName}", firstName, lastName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
