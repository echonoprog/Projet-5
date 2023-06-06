package com.ocs.safetynet.controller;


import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.service.FirestationService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FirestationsController {
    private FirestationService firestationService;

    public FirestationsController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping("/firestations")
    public List<Firestation> getAllFirestations() {

        return firestationService.getAllFirestations();

    }
}