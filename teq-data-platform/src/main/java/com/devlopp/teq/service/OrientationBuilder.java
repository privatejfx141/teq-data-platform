package com.devlopp.teq.service;

public class OrientationBuilder implements IOrientationBuilder {
    private Orientation orientation;

    public OrientationBuilder() {
        orientation = new Orientation();
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
    public IOrientationBuilder setNumberOfClients(int numberOfClients) {
        orientation.numberOfClients = numberOfClients;
        return this;
    }

    @Override
    public IOrientationBuilder setEndDate(String endDate) {
        orientation.endDate = endDate;
        return this;
    }
}
