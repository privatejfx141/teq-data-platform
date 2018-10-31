package com.devlopp.teq.client;

import java.util.ArrayList;

import com.devlopp.teq.address.Address;

public class Client {
    protected int id = -1;
    protected int idType = -1;
    protected String birthDate = "";
    protected String PhoneNumber = "";
    protected String EmailAddress = "";
    protected boolean consent;
    protected String language = "";
    protected Address address = null;

    /**
     * Returns the ID of the client as it appears in the TEQ database.
     * 
     * @return client ID
     */
    public int getId() {
        return id;
    }

    public int getIdType() {
        return idType;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public boolean getConsent() {
        return consent;
    }

    public String getLanguage() {
        return language;
    }
    
    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        ArrayList<String> repr = new ArrayList<>();
        if (id != -1) {
            repr.add("" + id);
        }
        if (idType != -1) {
            repr.add(String.format("%d", idType));
        }
        if (!birthDate.isEmpty()) {
            repr.add(birthDate);
        }
        if (!PhoneNumber.isEmpty()) {
            repr.add(PhoneNumber);
        }
        if (!EmailAddress.isEmpty()) {
            repr.add(EmailAddress);
        }
        if (!language.isEmpty()) {
            repr.add(language);
        }
        if (address != null && address.getId() != -1) {
            repr.add("" + address.getId());
        }
        return "Client(" + String.join(", ", repr) + ")";
    }
}
