package com.teq.service;

import java.util.ArrayList;
import java.util.List;

public abstract class Service {
    private int id;
    private int clientId;
    private String language;
    private String organizationType;
    private String referredBy;
    private String updateReason;
    private String serviceType;
    private List<String> essentialSkill = new ArrayList<String>();
    private List<String> supportServices = new ArrayList<String>();
    private List<String> targetGroups = new ArrayList<String>();

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

    public String getReferredBy() {
        return referredBy;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public String getServiceType() {
        return serviceType;
    }

    public List<String> getEssentialSkills() {
        return essentialSkill;
    }

    public List<String> getSupportServices() {
        return supportServices;
    }

    public List<String> getTargetGroups() {
        return targetGroups;
    }
}
