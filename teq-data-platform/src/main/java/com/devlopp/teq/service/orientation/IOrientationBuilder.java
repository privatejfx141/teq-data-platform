package com.devlopp.teq.service.orientation;

import com.devlopp.teq.service.IServiceBuilder;

public interface IOrientationBuilder extends IServiceBuilder {
    public IOrientationBuilder setServiceReceived(String serviceReceived);

    public IOrientationBuilder setTotalLength(String totalLength);

    public IOrientationBuilder setLengthHours(int lengthHours);

    public IOrientationBuilder setLengthMinutes(int lengthMinutes);

    public IOrientationBuilder setNumberOfClients(String numberOfClients);

    public IOrientationBuilder setEndDate(String endDate);

    public Orientation create();
}
