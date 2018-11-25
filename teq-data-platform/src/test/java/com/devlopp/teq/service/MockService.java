package com.devlopp.teq.service;

import java.util.ArrayList;
import java.util.List;

public class MockService extends Service {

    public static class Builder implements IServiceBuilder {

        private MockService mockService = new MockService();

        @Override
        public IServiceBuilder setId(int id) {
            mockService.id = 1;
            return this;
        }

        @Override
        public IServiceBuilder setClientId(int clientId) {
            mockService.clientId = 1000;
            return this;
        }

        @Override
        public IServiceBuilder setPostalCode(String postalCode) {
            mockService.postalCode = "A1A2B2";
            return this;
        }

        @Override
        public IServiceBuilder setLanguage(String language) {
            mockService.language = "English";
            return this;
        }

        @Override
        public IServiceBuilder setReferredBy(String referredBy) {
            mockService.referredBy = "Referred by";
            return this;
        }

        @Override
        public IServiceBuilder setOrganizationType(String organizationType) {
            mockService.organizationType = "Organization type";
            return this;
        }

        @Override
        public IServiceBuilder setUpdateReason(String updateReason) {
            mockService.updateReason = "Update reason";
            return this;
        }

        @Override
        public IServiceBuilder setServiceType(String serviceType) {
            mockService.serviceType = "A";
            return this;
        }

        @Override
        public IServiceBuilder setEssentialSkills(List<String> essentialSkills) {
            mockService.essentialSkills = new ArrayList<>();
            return this;
        }

        @Override
        public IServiceBuilder setSupportServices(List<String> supportServices) {
            mockService.supportServices = new ArrayList<>();
            return this;
        }

        @Override
        public IServiceBuilder setTargetGroups(List<String> targetGroups) {
            mockService.targetGroups = new ArrayList<>();
            return this;
        }

        @Override
        public IServiceBuilder setChildCares(List<NewcomerChildCare> childCares) {
            mockService.childCares = new ArrayList<>();
            return this;
        }

        @Override
        public MockService create() {
            return mockService;
        }

    }

    private MockService() {
        // empty constructor
    }

}
