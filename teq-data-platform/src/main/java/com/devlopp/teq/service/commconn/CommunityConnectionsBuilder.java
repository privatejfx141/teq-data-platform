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
        community.eventType = eventType.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setMainTopic(String mainTopic) {
        community.mainTopic = mainTopic.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setServiceReceived(String serviceReceived) {
        community.serviceReceived = serviceReceived.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setParticipants(String participants) {
        community.participants = participants.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setVolunteers(boolean hasVolunteers) {
        community.hasVolunteers = hasVolunteers;
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setStatus(String status) {
        community.status = status.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setReasonForLeave(String reasonForLeave) {
        community.reasonForLeave = reasonForLeave.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setStartDate(String startDate) {
        community.startDate = startDate.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setEndDate(String endDate) {
        community.endDate = endDate.trim();
        return this;
    }

    @Override
    public ICommunityConnectionsBuilder setProjectedEndDate(String projectedEndDate) {
        community.projectedEndDate = projectedEndDate.trim();
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
