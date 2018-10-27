package com.teq.service;

import java.util.List;

public interface IAssessmentBuilder {
	
    public IAssessmentBuilder setStartDate(String startDate);

    public IAssessmentBuilder setLanguageGoal(String languageGoal);

    public IAssessmentBuilder setOtherGoal(String otherGoal);

    public IAssessmentBuilder setIntendsCitizenship(boolean wantsCitizenship);

    public IAssessmentBuilder setReqSupportServices(boolean reqSupportServices);

    public IAssessmentBuilder setPlanComplete(boolean planComplete);

    public IAssessmentBuilder setEndDate(String endDate);

	public IAssessmentBuilder setIncrease(List<String> increase);

	public IAssessmentBuilder setNonIRCCServices(List<String> nonIRCCServices);
    
}
