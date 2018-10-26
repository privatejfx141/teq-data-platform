package com.teq.entities;

public interface IAssessmentBuilder extends IServiceBuilder{
	
	public IAssessmentBuilder setServiceId(int ServiceId);
	
	public IAssessmentBuilder setStartDate(String StartDate);
	
	public IAssessmentBuilder setLanguageGoal(String LanguageGoal);
	
	public IAssessmentBuilder setOtherGoal(String OtherGoal);
	
	public IAssessmentBuilder setIntendsCitizenship(boolean IntendsCitizenship);
	
	public IAssessmentBuilder setReqSupportServices(boolean ReqSupportServices);
	
	public IAssessmentBuilder setPlanComplete(boolean PlanComplete);
	
	public IAssessmentBuilder setEndDate(String EndDate);

}
