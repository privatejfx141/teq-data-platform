package com.teq.service;

import java.util.List;

public abstract class Service {
    private int id;
    private int clientId;
    private String language;
    private String organizationType;
    private String updateReason;
    private String serviceType;
    private List<String> essentialSkill;
    private List<String> supportServices;
    private List<String> targetGroups;

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public String getLanguage() {
        return language;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public String getServiceType() {
        return serviceType;
    }

    public List<String> getEssentialSkill() {
        return essentialSkill;
    }

    public List<String> getSupportServices() {
        return supportServices;
    }

    public List<String> getTargetGroups() {
        return targetGroups;
    }
}
