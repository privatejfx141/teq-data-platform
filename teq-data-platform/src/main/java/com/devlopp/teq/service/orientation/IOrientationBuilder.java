package com.devlopp.teq.service.orientation;

public interface IOrientationBuilder {
    public IOrientationBuilder setServiceReceived(String serviceReceived);

    public IOrientationBuilder setTotalLength(String totalLength);

    public IOrientationBuilder setLengthHours(int lengthHours);

    public IOrientationBuilder setLengthMinutes(int lengthMinutes);

    public IOrientationBuilder setNumberOfClients(int numberOfClients);

    public IOrientationBuilder setEndDate(String endDate);
}
