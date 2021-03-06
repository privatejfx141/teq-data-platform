package com.devlopp.teq.service.assessment;

public class FindEmployment {
    private String timeFrame;
    private String minExp;
    private String skillLevel;
    private String intends;

    public FindEmployment(String timeFrame, String minExp, String skillLevel, String intends) {
        this.timeFrame = timeFrame.trim();
        this.minExp = minExp.trim();
        this.skillLevel = skillLevel.trim();
        this.intends = intends.trim();
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public String getMinExperience() {
        return minExp;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public String getIntendsToObtain() {
        return intends;
    }

    public String toString() {
        return String.format("FindEmployment(%s, %s, %s, %s)", timeFrame, minExp, skillLevel, intends);
    }
}
