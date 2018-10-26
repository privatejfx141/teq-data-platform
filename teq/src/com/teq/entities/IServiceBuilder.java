package com.teq.entities;

public interface IServiceBuilder {

    public IServiceBuilder setId(int id);
    
    public IServiceBuilder setClientId(int ClientId);
    
    public IServiceBuilder setLanguage(String Language);
    
    public IServiceBuilder setOrganizationType(String OrganizationType);
    
    public IServiceBuilder setUpdateReason(String UpdateReason);
    
    public IServiceBuilder setServiceType(String ServiceType);
    
    public Client create();
    
}
