package com.teq.entities;

import java.util.ArrayList;

public class Client {

	public int id = -1;
	public int IdType = -1;
	public String Birthdate = "";
	public String PhoneNumber = "";
	public String EmailAddress = "";
	public boolean Consent;
	public String Language = "";
	public Address Address = null;
	
    /**
     * Returns the ID of the client as it appears in the TEQ database.
     * 
     * @return client ID
     */
    public int getId() {
        return id;
    }

    public int getIdType() {
        return IdType;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public boolean getConsent() {
        return Consent;
    }
    
    public String getLanguage() {
        return Language;
    }
    
    public Address getAddress() {
    	return Address;
    }

    @Override
    public String toString() {
        ArrayList<String> repr = new ArrayList<>();
        if (id != -1) {
            repr.add("" + id);
        }
        if (IdType != -1) {
            repr.add(String.format("%d", IdType));
        }
        if (!Birthdate.isEmpty()) {
            repr.add(Birthdate);
        }
        if (!PhoneNumber.isEmpty()) {
            repr.add(PhoneNumber);
        }
        if (!EmailAddress.isEmpty()) {
            repr.add(EmailAddress);
        }
        if (!Language.isEmpty()) {
            repr.add(Language);
        }
        if (Address != null && Address.id != -1) {
            repr.add("AddressId " + Address.id);
        }
        return "Client(" + String.join(", ", repr) + ")";
    }
}
