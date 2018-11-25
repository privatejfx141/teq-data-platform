package com.devlopp.teq.service.employment;

public class LongTermInterventionBuilder {

    private LongTermIntervention intervention;

    public LongTermInterventionBuilder() {
        intervention = new LongTermIntervention();
    }

    public LongTermInterventionBuilder setServiceRecieved(String service) {
        intervention.serviceRecieved = service.trim();
        return this;
    }

    public LongTermInterventionBuilder setStatus(String status) {
        intervention.status = status.trim();
        return this;
    }

    public LongTermInterventionBuilder setReasonForLeave(String reason) {
        intervention.reasonForLeave = reason.trim();
        return this;
    }

    public LongTermInterventionBuilder setStartDate(String date) {
        intervention.startDate = date.trim();
        return this;
    }

    public LongTermInterventionBuilder setEndDate(String date) {
        intervention.endDate = date.trim();
        return this;
    }

    public LongTermInterventionBuilder setEmployerSize(String size) {
        intervention.employerSize = size.trim();
        return this;
    }

    public LongTermInterventionBuilder setPlacement(String placement) {
        intervention.placement = placement.trim();
        return this;
    }

    public LongTermInterventionBuilder setAverageHoursPerWeek(String hours) {
        intervention.averageHoursPerWeek = hours.trim();
        return this;
    }

    public LongTermInterventionBuilder setMetMentorAt(String metMentorAt) {
        intervention.metMentorAt = metMentorAt.trim();
        return this;
    }

    public LongTermInterventionBuilder setHoursPeerWeek(String hours) {
        intervention.hoursPerWeek = hours.trim();
        return this;
    }

    public LongTermInterventionBuilder setProfession(String profession) {
        intervention.profession = profession.trim();
        return this;
    }

    public LongTermIntervention create() {
        return intervention;
    }

}
