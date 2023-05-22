package com.example.projet5.model;


import org.springframework.stereotype.Component;

@Component
public class Persons {

    private String firstNAme;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;



    public String getFirstNAme(){
        return firstNAme;
    }
    public void setFirstNAme(String firstNAme){
        this.firstNAme=firstNAme;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString(){
        return "persons {" +
                "firstName =" + firstNAme +
                "lastName =" + lastName +
                "address =" + address +
                "city =" + city +
                "zip =" + zip +
                "phone =" + phone +
                "email =" + email +
                "}";
    }
}
