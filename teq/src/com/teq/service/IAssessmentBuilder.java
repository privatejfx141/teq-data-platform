package com.teq.service;

public interface IAssessmentBuilder extends IServiceBuilder {
    public IAssessmentBuilder setStartDate(String startDate);

    public IAssessmentBuilder setLanguageGoal(String languageGoal);

    public IAssessmentBuilder setOtherGoal(String otherGoal);

    public IAssessmentBuilder setIntendsCitizenship(boolean wantsCitizenship);

    public IAssessmentBuilder setReqSupportServices(boolean reqSupportServices);

    public IAssessmentBuilder setPlanComplete(boolean planComplete);

    public IAssessmentBuilder setEndDate(String endDate);
}
