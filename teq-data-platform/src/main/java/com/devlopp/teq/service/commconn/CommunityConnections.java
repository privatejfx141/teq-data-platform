package com.devlopp.teq.service.commconn;

import com.devlopp.teq.service.Service;

public class CommunityConnections extends Service {
    protected String eventType;
    protected String mainTopic;
    protected String serviceReceived;
    protected int participants;
    protected boolean hasVolunteers;
    protected String reasonForLeave;
    protected String status;
    protected String startDate;
    protected String endDate;
    protected String projectedEndDate;
    protected int lengthHours;
    protected int lengthMinutes;

    public String getEventType() {
        return eventType;
    }

    public String getMainTopic() {
        return mainTopic;
    }

    public String getServiceReceived() {
        return serviceReceived;
    }

    public int getParticipants() {
        return participants;
    }

    public boolean getHasVolunteers() {
        return hasVolunteers;
    }

    public String getReasonForLeave() {
        return reasonForLeave;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getProjectedEndDate() {
        return projectedEndDate;
    }

    public int getLengthHours() {
        return lengthHours;
    }

    public int getlengthMinutes() {
        return lengthMinutes;
    }
}
