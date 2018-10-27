package com.teq.service;

import java.util.ArrayList;
import java.util.List;

public class Assessment extends Service {
	
	protected String id;
    protected String startDate;
    protected String languageSkillGoal;
    protected String otherSkillGoal;
    protected boolean wantsCitizenship;
    protected boolean reqSupportService;
    protected boolean planComplete;
    protected String endDate;
    protected List<String> increases;
    protected List<String> nonIRCCServices;

    public String getStartDate() {
        return startDate;
    }

    public String getLanguageSkillGoal() {
        return languageSkillGoal;
    }

    public String getOtherSkillGoal() {
        return otherSkillGoal;
    }

    public boolean getWantsCitizenship() {
        return wantsCitizenship;
    }

    public boolean getReqSupportService() {
        return reqSupportService;
    }

    public boolean getIsPlanComplete() {
        return planComplete;
    }

    public String getEndDate() {
        return endDate;
    }
    
    public List<String> getIncreases() {
    	return increases;
    }
    
    public List<String> getNonIRCCServices() {
    	return nonIRCCServices;
    }
    
    @Override
    public String toString() {
        ArrayList<String> repr = new ArrayList<>();
        if (!startDate.isEmpty()) {
            repr.add("Start Date" + startDate);
        }
        
        if (!languageSkillGoal.isEmpty()) {
            repr.add("Language Skill Goal" + languageSkillGoal);
        }
        
        if (!otherSkillGoal.isEmpty()) {
            repr.add("Other Skill Goal" + otherSkillGoal);
        }
        
        if (!endDate.isEmpty()) {
            repr.add("End Date" + endDate);
        }
        
        return "Assessment(" + String.join(", ", repr) + ")";
    }
}
