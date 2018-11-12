package com.devlopp.teq.service.orientation;

import com.devlopp.teq.service.ServiceBuilder;

public class OrientationBuilder extends ServiceBuilder implements IOrientationBuilder {
    private Orientation orientation;

    public OrientationBuilder() {
        service = new Orientation();
        setServiceType("O");
        orientation = (Orientation) service;
    }

    @Override
    public IOrientationBuilder setServiceReceived(String serviceReceived) {
        orientation.serviceReceived = serviceReceived;
        return this;
    }

    @Override
    public IOrientationBuilder setTotalLength(String totalLength) {
        orientation.totalLength = totalLength;
        return this;
    }

    @Override
    public IOrientationBuilder setLengthHours(int lengthHours) {
        orientation.lengthHours = lengthHours;
        return this;
    }

    @Override
    public IOrientationBuilder setLengthMinutes(int lengthMinutes) {
        orientation.lengthMinutes = lengthMinutes;
        return this;
    }

    @Override
    public IOrientationBuilder setNumberOfClients(String numberOfClients) {
        orientation.numberOfClients = numberOfClients;
        return this;
    }

    @Override
    public IOrientationBuilder setEndDate(String endDate) {
        orientation.endDate = endDate;
        return this;
    }

    
    
    @Override
    public Orientation create() {
        return orientation;
    }
}
