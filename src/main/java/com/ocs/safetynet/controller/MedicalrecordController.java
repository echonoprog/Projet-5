package com.ocs.safetynet.controller;


import com.ocs.safetynet.model.Medicalrecord;
import com.ocs.safetynet.service.MedicalrecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalrecordController {


    private MedicalrecordService medicalrecordService;

    @PostMapping("/medicalRecord")
    public ResponseEntity<Medicalrecord> addMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
        Medicalrecord addMedicalrecord = medicalrecordService.addMedicalrecord(medicalrecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(addMedicalrecord);
    }

    @PutMapping("/medicalRecord")
    public ResponseEntity<Medicalrecord> updateMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
        Medicalrecord updatedMedicalrecord = medicalrecordService.updateMedicalrecord(medicalrecord);
        return ResponseEntity.ok(updatedMedicalrecord);
    }

    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<Void> deleteMedicalrecord(@PathVariable String firstName, @PathVariable String lastName) {
        medicalrecordService.deleteMedicalrecord(firstName, lastName);
        return ResponseEntity.noContent().build();
    }
}
