package com.teq.entities;

public class Address {

    protected int id;
    
    protected String postalCode;
    
    protected int streetNumber;
    
    protected String streetName;
    
    protected String streetDirection;
    
    protected String city;
    
    protected String province;
   
    public int getId() {
        return id;
    }
    
    public String getPostalCode() {
        return postalCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetDirection() {
        return streetDirection;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }
    
}
