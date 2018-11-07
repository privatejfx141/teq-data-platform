package com.devlopp.teq.service.employment;

public class LongTermInterventionBuilder {

    private LongTermIntervention intervention;

    public LongTermInterventionBuilder() {
        intervention = new LongTermIntervention();
    }

    public LongTermInterventionBuilder setServiceRecieved(String service) {
        intervention.serviceRecieved = service;
        return this;
    }

    public LongTermInterventionBuilder setStatus(String status) {
        intervention.status = status;
        return this;
    }

    public LongTermInterventionBuilder setReasonForLeave(String reason) {
        intervention.reasonForLeave = reason;
        return this;
    }

    public LongTermInterventionBuilder setStartDate(String date) {
        intervention.startDate = date;
        return this;
    }

    public LongTermInterventionBuilder setEndDate(String date) {
        intervention.endDate = date;
        return this;
    }

    public LongTermInterventionBuilder setEmployerSize(int size) {
        intervention.employerSize = size;
        return this;
    }

    public LongTermInterventionBuilder setPlacement(String placement) {
        intervention.placement = placement;
        return this;
    }

    public LongTermInterventionBuilder setAverageHoursPerWeek(String hours) {
        intervention.averageHoursPerWeek = hours;
        return this;
    }

    public LongTermInterventionBuilder setMetMentorAt(String metMentorAt) {
        intervention.metMentorAt = metMentorAt;
        return this;
    }

    public LongTermInterventionBuilder setHoursPeerWeek(int hours) {
        intervention.hoursPerWeek = hours;
        return this;
    }

    public LongTermInterventionBuilder setProfession(String profession) {
        intervention.profession = profession;
        return this;
    }

    public LongTermIntervention build() {
        return intervention;
    }

}
