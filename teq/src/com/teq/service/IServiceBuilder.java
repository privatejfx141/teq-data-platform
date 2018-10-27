package com.teq.service;

import com.teq.client.Client;

public interface IServiceBuilder {
    public IServiceBuilder setId(int id);

    public IServiceBuilder setClientId(int clientId);

    public IServiceBuilder setLanguage(String language);

    public IServiceBuilder setOrganizationType(String organizationType);

    public IServiceBuilder setUpdateReason(String updateReason);

    public IServiceBuilder setServiceType(String serviceType);

    public Client create();
}
