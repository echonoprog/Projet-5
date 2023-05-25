package com.ocs.safetynet.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationsController {

    @GetMapping("/firestation")
    public void sayhello() {

        System.out.println("firestation");

    }
}