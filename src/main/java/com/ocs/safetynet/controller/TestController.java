package com.example.projet5.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/w")
    public void sayhello(){

        System.out.println("Hello");
    }
    @GetMapping("/n")
    public String sayhello2(){

        System.out.println("Hello");
        return "On est un hello via N";
    }
}
