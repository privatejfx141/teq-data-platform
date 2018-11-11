package com.devlopp.teq.generics;

import java.util.EnumMap;

public enum Templates {
    CLIENT_PROFILE,
    NEEDS_ASSESSMENT_REFERRALS,
    COMMUNITY_CONNECTIONS,
    INFO_ORIENT,
    EMPLOYMENT,
    LT_CLIENT_ENROL,
    LT_COURSE_SETUP,
    LT_CLIENT_EXIT;
    
    public static String getTemplateName(Templates template) {
        EnumMap<Templates, String> templates = new EnumMap<Templates, String>(Templates.class);
        templates.put(CLIENT_PROFILE, "Client Profile");
        
        return templates.get(template);
    }    
}
