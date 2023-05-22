package com.example.projet5.model;

import org.springframework.stereotype.Component;

@Component
public class Firestations {

    private String address;
    private String station;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public String getStation(){
        return station;
    }

    public void setStation(String station){
        this.station=station;
    }

    @Override
    public String toString(){
        return "station {" +
                "address =" + address +
                "station =" + station +
                "}";
    }
}

