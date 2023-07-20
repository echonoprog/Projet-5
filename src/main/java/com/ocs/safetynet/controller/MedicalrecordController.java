package com.ocs.safetynet.controller;


import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.service.MedicalrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class MedicalrecordController {

    private final Logger logger = LoggerFactory.getLogger(MedicalrecordController.class);

    private MedicalrecordService medicalrecordService;

    public MedicalrecordController(MedicalrecordService medicalrecordService){
        this.medicalrecordService = medicalrecordService;
    }

    @GetMapping("medicalRecord")
    public List<Medicalrecord> getAllMedicalrecords() {
        logger.info("All Medicalrecords");
        return medicalrecordService.getAllMedicalrecords();
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<Medicalrecord> addMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
        logger.info("Add Medicalrecords");
        Medicalrecord addMedicalrecord = medicalrecordService.addMedicalrecord(medicalrecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(addMedicalrecord);
    }

    @PutMapping("/medicalRecord/{firstName}&{lastName}")
    public ResponseEntity<Medicalrecord> updateMedicalrecord(@PathVariable String firstName, @PathVariable String lastName,@RequestBody Medicalrecord medicalrecord) {
        logger.info("Update Medicalrecords");
        Medicalrecord updatedMedicalrecord = medicalrecordService.updateMedicalrecord(firstName, lastName,medicalrecord);
        return ResponseEntity.ok(updatedMedicalrecord);
    }

    @DeleteMapping("/medicalRecord/{firstName}&{lastName}")
    public ResponseEntity<Void> deleteMedicalrecord(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("Delete Medicalrecords");
        medicalrecordService.deleteMedicalRecord(firstName, lastName);
        return ResponseEntity.noContent().build();
    }
}
