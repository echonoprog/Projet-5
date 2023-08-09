package com.ocs.safetynet.ControllerTest;

import com.ocs.safetynet.controller.FirestationController;
import com.ocs.safetynet.dto.FirestationStationNumberCountDto;
import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.service.FirestationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {


    @Autowired
    public MockMvc mvc;

    @MockBean
    private FirestationService firestationService;

    @InjectMocks
    private FirestationController firestationController;

    @Test
    public void testGetAllFirestations() throws Exception {
        List<Firestation> firestations = new ArrayList<>();

        when(firestationService.getAllFirestations()).thenReturn(firestations);

        mvc.perform(get("/firestation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAddFirestation() throws Exception {
        Firestation firestationToAdd = new Firestation();

        when(firestationService.addFirestation(any(Firestation.class))).thenReturn(firestationToAdd);

        mvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateFirestation() throws Exception {
        String address = "123 Main St";
        String station = "Station 1";
        Firestation updatedFirestation = new Firestation();

        when(firestationService.updateFirestation(eq(address), eq(station), any(Firestation.class))).thenReturn(updatedFirestation);

        mvc.perform(put("/firestation/{address}&{station}", address, station)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateFirestationNotFound() throws Exception {
        String address = "123 Main St";
        String station = "Station 1";
        Firestation updatedFirestation = new Firestation();

        when(firestationService.updateFirestation(eq(address), eq(station), any(Firestation.class))).thenReturn(null);

        mvc.perform(put("/firestation/{address}&{station}", address, station)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        String address = "123 Main St";
        String station = "Station 1";

        mvc.perform(delete("/firestation/{address}&{station}", address, station)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetFirestationCoverageByStationNumber() throws Exception {
        String stationNumber = "Station 1";
        FirestationStationNumberCountDto coverage = new FirestationStationNumberCountDto();

        when(firestationService.getFirestationCoverageByStationNumber(eq(stationNumber))).thenReturn(coverage);

        mvc.perform(get("/firestation/{stationNumber}", stationNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetFirestationCoverageByStationNumberNotFound() throws Exception {
        String stationNumber = "Station 1";

        when(firestationService.getFirestationCoverageByStationNumber(eq(stationNumber))).thenReturn(null);

        mvc.perform(get("/firestation/{stationNumber}", stationNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
