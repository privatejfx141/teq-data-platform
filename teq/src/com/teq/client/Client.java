package com.teq.client;

import java.util.ArrayList;

import com.teq.address.Address;

public class Client {
    public int id = -1;
    public int idType = -1;
    public String birthDate = "";
    public String PhoneNumber = "";
    public String EmailAddress = "";
    public boolean consent;
    public String language = "";
    public int addressId = -1;

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

    public int getAddressId() {
        return addressId;
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
        if (addressId != -1) {
            repr.add("" + addressId);
        }
        return "Client(" + String.join(", ", repr) + ")";
    }
}
