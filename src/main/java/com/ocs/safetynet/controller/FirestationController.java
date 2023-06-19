package com.ocs.safetynet.controller;


import com.ocs.safetynet.model.Firestation;
import com.ocs.safetynet.service.FirestationService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {
    private FirestationService firestationService;

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping("/firestation")
    public List<Firestation> getAllFirestations() {

        return firestationService.getAllFirestations();

    }

    @PostMapping("firestation")
    public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation firestation) {
        Firestation savedFirestation = firestationService.addFirestation(firestation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFirestation);

    }

    @PutMapping("/firestation")
    public ResponseEntity<Firestation> updateFirestation(@RequestBody Firestation firestation) {
        Firestation updatedFirestation = firestationService.updateFirestation(firestation);
            if (updatedFirestation != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedFirestation);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }
    @DeleteMapping("/firestation/{address}")
    public ResponseEntity<Void> deleteFirestation(@PathVariable String address) {
        firestationService.deleteFirestation(address);
        return ResponseEntity.noContent().build();

    }



    }













