package com.ocs.safetynet;

import com.ocs.safetynet.data.Data;
import com.ocs.safetynet.data.ReadData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.ocs.safetynet.data.ReadData.*;

@SpringBootApplication
public class SafetyNetApplication {

    public static void main(String[] args) {

        SpringApplication.run(SafetyNetApplication.class, args);
        ReadData.loadData();

    }



}
