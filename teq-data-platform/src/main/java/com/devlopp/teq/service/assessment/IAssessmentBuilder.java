package com.devlopp.teq.service.assessment;

import java.util.List;
import java.util.Map;

import com.devlopp.teq.service.IServiceBuilder;

public interface IAssessmentBuilder extends IServiceBuilder {
    public IAssessmentBuilder setStartDate(String startDate);

    public IAssessmentBuilder setLanguageGoal(String languageGoal);

    public IAssessmentBuilder setOtherGoal(String otherGoal);

    public IAssessmentBuilder setIntendsCitizenship(boolean wantsCitizenship);

    public IAssessmentBuilder setReqSupportServices(boolean reqSupportServices);

    public IAssessmentBuilder setPlanComplete(boolean planComplete);

    public IAssessmentBuilder setEndDate(String endDate);

    public IAssessmentBuilder setFindEmployment(String timeFrame, String minExp, String skillLevel, String intends);
    
    public IAssessmentBuilder setIncrease(Map<String, Boolean> increase);

    public IAssessmentBuilder setNonIRCCServices(List<String> nonIRCCServices);

    public Assessment create();
}
