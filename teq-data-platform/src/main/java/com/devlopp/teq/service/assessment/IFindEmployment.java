package com.devlopp.teq.service.assessment;

public interface IFindEmployment {
    /**
     * Returns the response for 'Find employment: TimeFrame'.
     * 
     * @return Find employment: TimeFrame
     */
    public String getTimeFrame();

    /**
     * Returns the response for 'Find employment: Minimum one year's work
     * experience?'.
     * 
     * @return Find employment: Minimum one year's work experience?
     */
    public String getMinExperience();

    /**
     * Returns the response for 'Find employment: Intends to work in an occupation
     * corresponding to which National Occupation Classification skill level?'.
     * 
     * @return Find employment: Intends to work in an occupation corresponding to
     *         which National Occupation Classification skill level?
     */
    public String getSkillLevel();

    /**
     * Returns the response for 'Find employment: Intends to obtain credential
     * recognition or obtain license to work in Canada?'.
     * 
     * @return Find employment: Intends to obtain credential recognition or obtain
     *         license to work in Canada?
     */
    public String getIntendsToObtain();
}
