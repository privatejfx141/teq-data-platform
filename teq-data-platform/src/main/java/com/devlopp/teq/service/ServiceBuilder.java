package com.devlopp.teq.service;

import java.util.List;

import com.devlopp.teq.service.assessment.Assessment;

public abstract class ServiceBuilder implements IServiceBuilder {
    protected Service service;

    @Override
    public IServiceBuilder setId(int id) {
        service.id = id;
        return this;
    }

    @Override
    public IServiceBuilder setClientId(int clientId) {
        service.clientId = clientId;
        return this;
    }

    @Override
    public IServiceBuilder setPostalCode(String postalCode) {
        service.postalCode = postalCode;
        return this;
    }

    @Override
    public IServiceBuilder setLanguage(String language) {
        service.language = language;
        return this;
    }

    @Override
    public IServiceBuilder setReferredBy(String referredBy) {
        service.referredBy = referredBy;
        return this;
    }

    @Override
    public IServiceBuilder setOrganizationType(String organizationType) {
        service.organizationType = organizationType;
        return this;
    }

    @Override
    public IServiceBuilder setUpdateReason(String updateReason) {
        service.updateReason = updateReason;
        return this;
    }

    @Override
    public IServiceBuilder setServiceType(String serviceType) {
        service.serviceType = serviceType;
        return this;
    }

    @Override
    public IServiceBuilder setEssentialSkills(List<String> essentialSkills) {
        service.essentialSkills = essentialSkills;
        return this;
    }

    @Override
    public IServiceBuilder setSupportServices(List<String> supportServices) {
        service.supportServices = supportServices;
        return this;
    }

    @Override
    public IServiceBuilder setTargetGroups(List<String> targetGroups) {
        service.targetGroups = targetGroups;
        return this;
    }

    @Override
    public IServiceBuilder setChildCares(List<NewcomerChildCare> childCares) {
        service.childCares = childCares;
        return this;
    }
    
    @Override
    public Service create() {
        return service;
    }
}
