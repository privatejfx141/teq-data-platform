package com.devlopp.teq.service;

public class NewcomerChildCare {
    private int id = -1;
    private int serviceId = -1;
    private String age;
    private String careType;

    public NewcomerChildCare(int serviceId, String age, String careType) {
        this.serviceId = serviceId;
        this.age = age;
        this.careType = careType;
    }

    public NewcomerChildCare(int id, int serviceId, String age, String careType) {
        this(serviceId, age, careType);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getAge() {
        return age;
    }

    public String getCareType() {
        return careType;
    }
}
