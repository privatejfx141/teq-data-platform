package com.devlopp.teq.service;

import java.util.List;

public interface IServiceBuilder {
	
    public IServiceBuilder setId(int id);

    public IServiceBuilder setClientId(int clientId);

    public IServiceBuilder setPostalCode(String postalCode);
    
    public IServiceBuilder setLanguage(String language);

    public IServiceBuilder setReferredBy(String referredBy);
    
    public IServiceBuilder setOrganizationType(String organizationType);

    public IServiceBuilder setUpdateReason(String updateReason);

    public IServiceBuilder setServiceType(String serviceType);
    
    public IServiceBuilder setEssentialSkills(List<String> essentialSkills);

    public IServiceBuilder setSupportServices(List<String> supportServices);

    public IServiceBuilder setTargetGroups(List<String> targetGroups);
    
}
