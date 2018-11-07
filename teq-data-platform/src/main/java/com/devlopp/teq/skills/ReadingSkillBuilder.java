package com.devlopp.teq.skills;

public class ReadingSkillBuilder implements IReadingSkillBuilder {
	
    private ReadingSkill readingSkill;

    public ReadingSkillBuilder() {
    	readingSkill = new ReadingSkill();
    }
	
	@Override
	public IReadingSkillBuilder setLevel(int level) {
		readingSkill.level = level;
		return this;
    }
    
    @Override
	public IReadingSkillBuilder setValue(String value) {
		readingSkill.value = value;
		return this;
	}
}
