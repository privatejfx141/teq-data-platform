package com.teq.service;

import java.util.List;

public interface IServiceBuilder {
	
    public IServiceBuilder setId(int id);

    public IServiceBuilder setClientId(int clientId);

    public IServiceBuilder setLanguage(String language);

    public IServiceBuilder setOrganizationType(String organizationType);

    public IServiceBuilder setUpdateReason(String updateReason);

    public IServiceBuilder setServiceType(String serviceType);
    
    public IServiceBuilder setEssentialSkill(List<String> essentialSkills);

    public IServiceBuilder setSupportServices(List<String> supportServices);

    public IServiceBuilder getTargetGroups(List<String> targetGroups);
    
}
