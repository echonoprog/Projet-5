package com.ocs.safetynet.data;

import ch.qos.logback.classic.BasicConfigurator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadData {

    public static void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {

            Data data = objectMapper.readValue(new File("src/main/resources/data.json"), Data.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}