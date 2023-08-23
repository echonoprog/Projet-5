package com.ocs.safetynet.data;


import com.fasterxml.jackson.databind.ObjectMapper;



import java.io.File;
import java.io.IOException;


public class ReadData {



    public static void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();


        try {

            File jsonFile = new File("src/main/resources/data.json");
            System.out.println( " Chemin du fichier JSON : " + jsonFile.getAbsolutePath());

            Data data  = objectMapper.readValue(jsonFile, Data.class);

            System.out.println(data);

            System.out.println("Persons : " + data.getPersons());

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}