package com.ocs.safetynet.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalrecordsController {


    @GetMapping("/medicalRecord")
    public void sayhello() {

        System.out.println("medicalRecord");

    }
}