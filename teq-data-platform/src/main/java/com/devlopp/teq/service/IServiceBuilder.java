package com.devlopp.teq.service;

import java.util.List;

public interface IServiceBuilder {
    /**
     * Sets the ID of the service.
     * 
     * @param id The ID of the service.
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setId(int id);

    /**
     * Sets the ID of the client.
     * 
     * @param clientId The ID of the client.
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setClientId(int clientId);

    /**
     * Sets the postal code of where the client was served.
     * 
     * @param postalCode The postal code of the service.
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setPostalCode(String postalCode);

    /**
     * Sets the language of the service.
     * 
     * @param language The main language of the service
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setLanguage(String language);

    /**
     * Sets how the client was referred to the service.
     * 
     * @param referredBy How the client was referred to the service
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setReferredBy(String referredBy);

    /**
     * Sets the type of organization that provided the service.
     * 
     * @param organizationType The type of organization that provided the service
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setOrganizationType(String organizationType);

    /**
     * Sets the reason for updating the record.
     * 
     * @param updateReason The reason for updating the record
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setUpdateReason(String updateReason);

    /**
     * Sets the subtype descriminator of the record.
     * 
     * @param serviceType The subtype descriminator of the record
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setServiceType(String serviceType);

    /**
     * Sets the list of essential skills provided by the service.
     * 
     * @param essentialSkills The list of essential skills provided by the service
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setEssentialSkills(List<String> essentialSkills);

    /**
     * Sets the list of support services provided by the service
     * 
     * @param supportServices The list of support services provided by the service
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setSupportServices(List<String> supportServices);

    /**
     * Sets the list of intended target groups for the service.
     * 
     * @param targetGroups The list of intended target groups for the service
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setTargetGroups(List<String> targetGroups);

    /**
     * Sets the list of newcomer child care responses for the service.
     * 
     * @param targetGroups The list of newcomer child care responses for the service
     * @return This builder object to allow for chaining of calls to set methods
     */
    public IServiceBuilder setChildCares(List<NewcomerChildCare> childCares);

    public Service create();
}
