package com.devlopp.teq.service.employment;

public class LongTermInterventionBuilder {

    private LongTermIntervention intervention;

    public LongTermInterventionBuilder() {
        intervention = new LongTermIntervention();
    }

    public void setServiceRecieved(String service) {
        intervention.serviceRecieved = service;
    }

    public void setStatus(String status) {
        intervention.status = status;
    }

    public void setReasonForLeave(String reason) {
        intervention.reasonForLeave = reason;
    }

    public void setStartDate(String date) {
        intervention.startDate = date;
    }

    public void setEndDate(String date) {
        intervention.endDate = date;
    }

    public void setEmployerSize(int size) {
        intervention.employerSize = size;
    }

    public void setPlacement(String placement) {
        intervention.placement = placement;
    }

    public void setAverageHoursPerWeek(String hours) {
        intervention.averageHoursPerWeek = hours;
    }

    public void setMetMentorAt(String metMentorAt) {
        intervention.metMentorAt = metMentorAt;
    }

    public void setHoursPeerWeek(int hours) {
        intervention.hoursPerWeek = hours;
    }

    public void setProfession(String profession) {
        intervention.profession = profession;
    }

    public LongTermIntervention build() {
        return intervention;
    }

}
