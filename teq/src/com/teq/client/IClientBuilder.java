package com.teq.client;

import com.teq.address.Address;

public interface IClientBuilder {
    public IClientBuilder setId(int id);

    public IClientBuilder setIdType(int IdType);

    public IClientBuilder setBirthDate(String Birthdate);

    public IClientBuilder setPhoneNumber(String PhoneNumber);

    public IClientBuilder setEmailAddress(String EmailAddress);

    public IClientBuilder setConsent(boolean consent);

    public IClientBuilder setLanguage(String language);

    public IClientBuilder setAddressId(int addressId);
    
    public IClientBuilder setAddress(Address address);

    public Client create();
}
