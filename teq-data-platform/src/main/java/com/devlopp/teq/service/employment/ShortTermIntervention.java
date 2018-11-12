package com.devlopp.teq.service.employment;

public class ShortTermIntervention {

    private String serviceRecieved;
    private String date;

    public ShortTermIntervention(String serviceRecieved, String date) {
        this.serviceRecieved = serviceRecieved;
        this.date = date;
    }

    public String getServiceRecieved() {
        return serviceRecieved;
    }

    public String getDate() {
        return date;
    }

}
