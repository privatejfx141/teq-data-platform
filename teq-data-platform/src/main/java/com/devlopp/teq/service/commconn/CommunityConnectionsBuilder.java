package com.devlopp.teq.service.commconn;

import com.devlopp.teq.service.ServiceBuilder;

public class CommunityConnectionsBuilder extends ServiceBuilder implements ICommunityConnectionsBuilder {
    private CommunityConnections community;

    public CommunityConnectionsBuilder() {
        service = new CommunityConnections();
        setServiceType("C");
        community = (CommunityConnections) service;
    }

    @Override
    public ICommunityConnectionsBuilder setEventType(String eventType) {
        community.eventType = eventType;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setMainTopic(String mainTopic) {
        community.mainTopic = mainTopic;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setServiceReceived(String serviceReceived) {
        community.serviceReceived = serviceReceived;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setParticipants(int participants) {
        community.participants = participants;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setVolunteers(boolean hasVolunteers) {
        community.hasVolunteers = hasVolunteers;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setStatus(String status) {
        community.status = status;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setReasonForLeave(String reasonForLeave) {
        community.reasonForLeave = reasonForLeave;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setStartDate(String startDate) {
        community.startDate = startDate;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setEndDate(String endDate) {
        community.endDate = endDate;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setProjectedEndDate(String projectedEndDate) {
        community.projectedEndDate = projectedEndDate;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setLengthHours(int lengthHours) {
        community.lengthHours = lengthHours;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setLengthMinutes(int lengthMinutes) {
        community.lengthMinutes = lengthMinutes;
        return this;
    }

    @Override
    public CommunityConnections create() {
        return community;
    }
}
