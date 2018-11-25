package com.devlopp.teq.service.assessment;

import java.util.List;
import java.util.Map;

import com.devlopp.teq.service.ServiceBuilder;

public class AssessmentBuilder extends ServiceBuilder implements IAssessmentBuilder {
    private Assessment assessment;

    public AssessmentBuilder() {
        service = new Assessment();
        setServiceType("A");
        assessment = (Assessment) service;
    }

    @Override
    public IAssessmentBuilder setStartDate(String startDate) {
        assessment.startDate = startDate.trim();
        return this;
    }

    @Override
    public IAssessmentBuilder setLanguageGoal(String languageGoal) {
        assessment.languageSkillGoal = languageGoal.trim();
        return this;
    }

    @Override
    public IAssessmentBuilder setOtherGoal(String otherGoal) {
        assessment.otherSkillGoal = otherGoal.trim();
        return this;
    }

    @Override
    public IAssessmentBuilder setIntendsCitizenship(boolean wantsCitizenship) {
        assessment.wantsCitizenship = wantsCitizenship;
        return this;
    }

    @Override
    public IAssessmentBuilder setReqSupportServices(boolean reqSupportServices) {
        assessment.reqSupportService = reqSupportServices;
        return this;
    }

    @Override
    public IAssessmentBuilder setPlanComplete(boolean planComplete) {
        assessment.planComplete = planComplete;
        return this;
    }

    @Override
    public IAssessmentBuilder setEndDate(String endDate) {
        assessment.endDate = endDate.trim();
        return this;
    }

    @Override
    public IAssessmentBuilder setFindEmployment(String timeFrame, String minExp, String skillLevel, String intends) {
        assessment.findEmployment = new FindEmployment(timeFrame, minExp, skillLevel, intends);
        return this;
    }

    @Override
    public IAssessmentBuilder setIncrease(Map<String, Boolean> increase) {
        assessment.increases = increase;
        return this;
    }

    @Override
    public IAssessmentBuilder setNonIRCCServices(List<String> nonIRCCServices) {
        assessment.nonIRCCServices = nonIRCCServices;
        return this;
    }

    @Override
    public Assessment create() {
        return assessment;
    }

}
