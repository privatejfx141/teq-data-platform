package com.devlopp.teq.address;

import java.util.ArrayList;

public class Address {
    protected int id = -1;
    protected String postalCode = "";
    protected int unitNumber = -1;
    protected int streetNumber = -1;
    protected String streetName = "";
    protected String streetDirection = "";
    protected String streetType = "";
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

    public int getUnitNumber() {
        return unitNumber;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetType() {
        return streetType;
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
        if (streetNumber != -1 && !streetName.isEmpty() && !streetType.isEmpty() && !streetDirection.isEmpty()) {
            String streetRepr = String.format("%d %s %s %s", streetNumber, streetName, streetType, streetDirection);
            if (unitNumber != -1) {
                streetRepr = unitNumber + "-" + streetRepr;
            }
            repr.add(streetRepr);
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
