package com.devlopp.teq.parser;

import java.util.HashMap;
import java.util.Map;

class ClientProfileMapper implements TemplateMapper {
    
    private static ClientProfileMapper mapper = null;
    
    private Map<String, String> map;
    
    private ClientProfileMapper() {
        map = new HashMap<String, String>();
        map.put("Processing Details", "processing_details");
        map.put("Unique Identifier", "id_type");
        map.put("Unique Identifier Value", "id");
        map.put("Date of Birth (YYYY-MM-DD)", "birth_date");
        map.put("Phone Number", "phone_number");
        map.put("Does the Client Have an Email Address", "has_email_address");
        map.put("Email Address", "email_address");
        map.put("Street Number", "street_number");
        map.put("Street Name", "street_name");
        map.put("Street Type", "street_type");
        map.put("Street Direction", "street_direction");
        map.put("Unit/Suite/Apt", "unit_number");
        map.put("City", "city");
        map.put("Province", "province");
        map.put("Postal Code", "postal_code");
        map.put("Official Language of Preference", "language");
        map.put("Consent for Future Research/Consultation", "conscents");
    }
    
    public static ClientProfileMapper getInstance() {
        if (mapper == null) {
            mapper = new ClientProfileMapper();
        }
        return mapper;
    }
    
    public String getAttribute(String excelColumnName) {
        return map.get(excelColumnName.trim());
    }
    
}
