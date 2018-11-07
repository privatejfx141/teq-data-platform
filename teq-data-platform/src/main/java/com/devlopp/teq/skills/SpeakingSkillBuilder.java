package com.devlopp.teq.skills;

public class SpeakingSkillBuilder implements ISpeakingSkillBuilder {
	
    private SpeakingSkill speakingSkill;

    public SpeakingSkillBuilder() {
    	speakingSkill = new SpeakingSkill();
    }
	
	@Override
	public ISpeakingSkillBuilder setLevel(int level) {
		speakingSkill.level = level;
		return this;
    }
    
    @Override
	public ISpeakingSkillBuilder setValue(String value) {
		speakingSkill.value = value;
		return this;
	}
}