package com.ocs.safetynet.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadData {

    public static void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Data data = objectMapper.readValue(new File("src/main/resources/data.json"), Data.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}