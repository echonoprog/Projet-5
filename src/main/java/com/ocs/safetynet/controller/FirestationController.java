package com.ocs.safetynet.controller;


import com.ocs.safetynet.dto.FirestationStationNumberCountDto;
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

    @PutMapping("/firestation/{address}&{station}")
    public ResponseEntity<Firestation> updateFirestation(@PathVariable String address, @PathVariable String station, @RequestBody Firestation firestation) {
        Firestation updatedFirestation = firestationService.updateFirestation(address, station, firestation);
        if (updatedFirestation != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedFirestation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/firestation/{address}&{station}")
    public ResponseEntity<Void> deleteFirestation(@PathVariable String address, @PathVariable String station) {
        firestationService.deleteFirestation(address, station);
        return ResponseEntity.noContent().build();
    }

    /** Endpoint pour récupérer les informations des personnes couvertes par la caserne de pompiers correspondante **/
    @GetMapping("/firestation/{stationNumber}")
    public ResponseEntity<FirestationStationNumberCountDto> getFirestationCoverageByStationNumber(@PathVariable  String stationNumber) {
        FirestationStationNumberCountDto firestationCoverage = firestationService.getFirestationCoverageByStationNumber(stationNumber);
        if (firestationCoverage != null) {
            return ResponseEntity.status(HttpStatus.OK).body(firestationCoverage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}













