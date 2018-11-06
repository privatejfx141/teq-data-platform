package com.devlopp.teq.service.employment;

import com.devlopp.teq.service.IServiceBuilder;

public interface IEmploymentBuilder extends IServiceBuilder {
    public IEmploymentBuilder setRegistration(boolean status);

    public IEmploymentBuilder setReferralTo(String referral);

    public IEmploymentBuilder setReferralDate(String date);

    public IEmploymentBuilder setEmploymentStatus(String employmentStatus);

    public IEmploymentBuilder setOccupationCanada(String occupation);

    public IEmploymentBuilder setOccupationIntended(String occupation);

    public IEmploymentBuilder setInterventionType(String intType);

    public IEmploymentBuilder setTimeSpentHours(int hours);

    public IEmploymentBuilder setTimeSpentMinutes(int minutes);
}
