package com.teq.service;

public class Orientation extends Service {
    protected String serviceReceived;
    protected String totalLength;
    protected int lengthHours;
    protected int lengthMinutes;
    protected int numberOfClients;
    protected String endDate;

    public String getServiceReceived() {
        return serviceReceived;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public int getLengthHours() {
        return lengthHours;
    }

    public int getLengthMinutesd() {
        return lengthMinutes;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public String getEndDate() {
        return endDate;
    }
}
