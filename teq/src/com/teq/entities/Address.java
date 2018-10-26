package com.teq.entities;

import java.util.ArrayList;

public class Address {
    protected int id = -1;
    protected String postalCode = "";
    protected int streetNumber = -1;
    protected String streetName = "";
    protected String streetDirection = "";
    protected String city = "";
    protected String province = "";

    /**
     * Returns the ID of the address as it appears in the TEQ database.
     * 
     * @return address ID
     */
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

    @Override
    public String toString() {
        ArrayList<String> repr = new ArrayList<>();
        if (id != -1) {
            repr.add("" + id);
        }
        if (!postalCode.isEmpty()) {
            repr.add(postalCode);
        }
        if (streetNumber != -1 && !streetName.isEmpty() && !streetDirection.isEmpty()) {
            repr.add(String.format("%d %s %s", streetNumber, streetName, streetDirection));
        }
        if (!city.isEmpty()) {
            repr.add(city);
        }
        if (!province.isEmpty()) {
            repr.add(province);
        }
        return "Address(" + String.join(", ", repr) + ")";
    }
}
