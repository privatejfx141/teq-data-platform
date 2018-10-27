package com.teq.client;

import com.teq.address.Address;

public class ClientBuilder implements IClientBuilder {
    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    @Override
    public IClientBuilder setId(int id) {
        client.id = id;
        return this;
    }

    @Override
    public IClientBuilder setIdType(int IdType) {
        client.IdType = IdType;
        return this;
    }

    @Override
    public IClientBuilder setBirthdate(String Birthdate) {
        client.Birthdate = Birthdate;
        return this;
    }

    @Override
    public IClientBuilder setPhoneNumber(String PhoneNumber) {
        client.PhoneNumber = PhoneNumber;
        return this;
    }

    @Override
    public IClientBuilder setEmailAddress(String EmailAddress) {
        client.EmailAddress = EmailAddress;
        return this;
    }

    @Override
    public IClientBuilder setConesnt(boolean consent) {
        client.Consent = consent;
        return this;
    }

    @Override
    public IClientBuilder setLanguage(String language) {
        client.Language = language;
        return this;
    }

    @Override
    public IClientBuilder setAddress(Address address) {
        client.Address = address;
        return this;
    }

    @Override
    public Client create() {
        return client;
    }
}
