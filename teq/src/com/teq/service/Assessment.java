package com.teq.service;

import java.util.List;

public class Assessment extends Service {
    private String startDate;
    private String languageSkillGoal;
    private String otherSkillGoal;
    private boolean wantsCitizenship;
    private boolean reqSupportService;
    private boolean planComplete;
    private String endDate;
    private List<String> increases;
    private List<String> nonIRCCServices;

    public String getStartDate() {
        return startDate;
    }

    public String getLanguageSkillGoal() {
        return languageSkillGoal;
    }

    public String getOtherSkillGoal() {
        return otherSkillGoal;
    }

    public boolean wantsCitizenship() {
        return wantsCitizenship;
    }

    public boolean reqSupportService() {
        return reqSupportService;
    }

    public boolean isPlanComplete() {
        return planComplete;
    }

    public String getEndDate() {
        return endDate;
    }
}
