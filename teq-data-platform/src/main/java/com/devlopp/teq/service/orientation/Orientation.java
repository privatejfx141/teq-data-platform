package com.devlopp.teq.service.orientation;

import java.util.HashMap;
import java.util.Map;

import com.devlopp.teq.service.Service;

public class Orientation extends Service {
    protected String serviceReceived;
    protected String totalLength;
    protected int lengthHours;
    protected int lengthMinutes;
    protected String numberOfClients;
    protected String endDate;
    protected Map<String, Boolean> topics = new HashMap<>();

    public String getServiceReceived() {
        return serviceReceived;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public int getLengthHours() {
        return lengthHours;
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }

    public String getNumberOfClients() {
        return numberOfClients;
    }

    public String getEndDate() {
        return endDate;
    }

    public Map<String, Boolean> getTopics() {
        return topics;
    }

    public void addTopic(String topic, boolean referrals) {
        topics.put(topic, referrals);
    }
}
