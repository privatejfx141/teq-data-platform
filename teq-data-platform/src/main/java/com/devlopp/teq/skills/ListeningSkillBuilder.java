package com.devlopp.teq.skills;

public class ListeningSkillBuilder implements IListeningSkillBuilder {
	
    private ListeningSkill listeningSkill;

    public ListeningSkillBuilder() {
    	listeningSkill = new ListeningSkill();
    }
	
	@Override
	public IListeningSkillBuilder setLevel(int level) {
		listeningSkill.level = level;
		return this;
    }
    
    @Override
	public IListeningSkillBuilder setValue(String value) {
		listeningSkill.value = value;
		return this;
	}
}
