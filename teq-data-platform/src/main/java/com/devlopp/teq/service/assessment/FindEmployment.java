package com.devlopp.teq.service.assessment;

public class FindEmployment implements IFindEmployment {
    private String timeFrame;
    private String minExp;
    private String skillLevel;
    private String intends;
    
    public FindEmployment(String timeFrame, String minExp, String skillLevel, String intends) {
        this.timeFrame = timeFrame;
        this.minExp = minExp;
        this.skillLevel = skillLevel;
        this.intends = intends;
    }

    @Override
    public String getTimeFrame() {
        return timeFrame;
    }

    @Override
    public String getMinExperience() {
        return minExp;
    }

    @Override
    public String getSkillLevel() {
        return skillLevel;
    }

    @Override
    public String getIntendsToObtain() {
        return intends;
    }
}
