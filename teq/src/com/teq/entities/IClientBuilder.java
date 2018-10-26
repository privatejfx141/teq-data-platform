package com.teq.entities;

public interface IClientBuilder {

    public IClientBuilder setId(int id);
    
    public IClientBuilder setIdType(int IdType);
    
    public IClientBuilder setBirthdate(String Birthdate);
    
    public IClientBuilder setPhoneNumber(String PhoneNumber);
    
    public IClientBuilder setEmailAddress(String EmailAddress);
    
    public IClientBuilder setConesnt(boolean consent);
    
    public IClientBuilder setLanguage(String language);
    
    public IClientBuilder setAddress(Address address);
    
    public Client create();
}
