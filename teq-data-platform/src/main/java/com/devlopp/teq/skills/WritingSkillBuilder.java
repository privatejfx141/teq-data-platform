package com.devlopp.teq.skills;

public class WritingSkillBuilder implements IWritingSkillBuilder {
	
    private WritingSkill writingSkill;

    public WritingSkillBuilder() {
    	writingSkill = new WritingSkill();
    }
	
	@Override
	public IWritingSkillBuilder setLevel(int level) {
		writingSkill.level = level;
		return this;
    }
    
    @Override
	public IWritingSkillBuilder setValue(String value) {
		writingSkill.value = value;
		return this;
	}
}