package com.devlopp.teq.service.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devlopp.teq.service.Service;

public class Assessment extends Service {
    protected String id;
    protected String startDate;
    protected String languageSkillGoal;
    protected String otherSkillGoal;
    protected boolean wantsCitizenship;
    protected boolean reqSupportService;
    protected boolean planComplete;
    protected String endDate;
    protected FindEmployment findEmployment;
    protected Map<String, Boolean> increases = new HashMap<String, Boolean>();
    protected List<String> nonIRCCServices = new ArrayList<String>();

    /**
     * Returns the start date of this assessment service.
     * 
     * @return start date of this assessment service
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Returns the client's language skill goal as stated in this assessment
     * service.
     * 
     * @return client's language skill goal
     */
    public String getLanguageSkillGoal() {
        return languageSkillGoal;
    }

    /**
     * Returns the client's miscellaneous skill goal as stated in this assessment
     * service.
     * 
     * @return client's miscellaneous skill goal
     */
    public String getOtherSkillGoal() {
        return otherSkillGoal;
    }

    /**
     * Returns whether the client wants citizenship as stated in this assessment
     * service.
     * 
     * @return whether the client wants citizenship
     */
    public boolean wantsCitizenship() {
        return wantsCitizenship;
    }

    /**
     * Returns whether the client required support services for this assessment
     * service.
     * 
     * @return Whether the client required support services
     */
    public boolean reqSupportService() {
        return reqSupportService;
    }

    /**
     * Returns whether the client's settlement plan is reviewed and complete.
     * 
     * @return Whether the client's settlement plan is reviewed and complete
     */
    public boolean isPlanComplete() {
        return planComplete;
    }

    /**
     * Returns the end date of this assessment service.
     * 
     * @return The end date of this assessment service
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns a list of fields of improvement in this assessment service.
     * 
     * @return The list of fields of improvement
     */
    public Map<String, Boolean> getIncreases() {
        return increases;
    }

    /**
     * Returns the responses for the find employment fields.
     * 
     * @return The responses for the find employment fields
     */
    public FindEmployment getFindEmployment() {
        return findEmployment;
    }

    /**
     * Adds a field of improvement to the service and whether or not the client had
     * referral.
     * 
     * @param increase field of improvement
     * @param referral whether or not the client had referral
     */
    public void addIncrease(String increase, boolean referral) {
        increases.put(increase, referral);
    }

    /**
     * Returns a list of non-IRCC services for this assessment service.
     * 
     * @return A list of non-IRCC services
     */
    public List<String> getNonIRCCServices() {
        return nonIRCCServices;
    }

    /**
     * Adds a non-IRCC service.
     * 
     * @param service non-IRCC service
     */
    public void addNonIRCCService(String service) {
        nonIRCCServices.add(service);
    }

    @Override
    public String toString() {
        ArrayList<String> repr = new ArrayList<>();
        if (!startDate.isEmpty()) {
            repr.add("Start Date: " + startDate);
        }
        if (!languageSkillGoal.isEmpty()) {
            repr.add("Language Skill Goal: " + languageSkillGoal);
        }
        if (!otherSkillGoal.isEmpty()) {
            repr.add("Other Skill Goal: " + otherSkillGoal);
        }
        if (!endDate.isEmpty()) {
            repr.add("End Date: " + endDate);
        }
        return "Assessment(" + String.join(", ", repr) + ")";
    }
}
