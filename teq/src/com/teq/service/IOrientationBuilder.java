package com.teq.service;

public interface IOrientationBuilder extends IServiceBuilder {
    public IEmploymentBuilder setServiceReceived(String serviceReceived);

    public IEmploymentBuilder setTotalLength(String totalLength);

    public IEmploymentBuilder setLengthHours(int lengthHours);

    public IEmploymentBuilder setLengthMinutes(int lengthMinutes);

    public IEmploymentBuilder setNumberOfClients(int numberOfClients);

    public IEmploymentBuilder setEndDate(String endDate);
}
