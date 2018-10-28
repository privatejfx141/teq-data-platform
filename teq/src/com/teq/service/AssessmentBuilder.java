package com.teq.service;

import java.util.List;

public class AssessmentBuilder implements IAssessmentBuilder {
	
    private Assessment assessment;

    public AssessmentBuilder() {
    	assessment = new Assessment();
    }
	
	@Override
	public IAssessmentBuilder setStartDate(String startDate) {
		assessment.startDate = startDate;
		return this;
	}

	@Override
	public IAssessmentBuilder setLanguageGoal(String languageGoal) {
		assessment.languageSkillGoal = languageGoal;
		return this;
	}

	@Override
	public IAssessmentBuilder setOtherGoal(String otherGoal) {
		assessment.otherSkillGoal = otherGoal;
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
		assessment.endDate = endDate;
		return this;
	}
	
	@Override
	public IAssessmentBuilder setIncrease(List<String> increase) {
		assessment.increases = increase;
		return this;
	}
	
	@Override
	public IAssessmentBuilder setNonIRCCServices(List<String> nonIRCCServices) {
		assessment.nonIRCCServices = nonIRCCServices;
		return this;
	}

}
