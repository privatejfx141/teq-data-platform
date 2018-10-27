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
        client.idType = IdType;
        return this;
    }

    @Override
    public IClientBuilder setBirthdate(String Birthdate) {
        client.birthDate = Birthdate;
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
    public IClientBuilder setConsent(boolean consent) {
        client.consent = consent;
        return this;
    }

    @Override
    public IClientBuilder setLanguage(String language) {
        client.language = language;
        return this;
    }

    @Override
    public IClientBuilder setAddressId(int addressId) {
        client.addressId = addressId;
        return this;
    }

    @Override
    public Client create() {
        return client;
    }
}
