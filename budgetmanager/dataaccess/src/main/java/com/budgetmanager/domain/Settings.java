package com.budgetmanager.domain;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class Settings {
    private String fullName;
    private String occupation;
    private String email;
    private String companyName;
    private String companyPhone;
    private String companyStreet;
    private int companyBuildingNumber;
    private String companyCity;
    private String companyCountry;

    public boolean isConfigured() throws NumberFormatException {
        Set<String> settings = new HashSet<>();
        settings.add(occupation);
        settings.add(email);
        settings.add(companyName);
        settings.add(companyPhone);
        settings.add(companyStreet);
        String companyBuildingNumberAsString;
        companyBuildingNumberAsString = String.valueOf(companyBuildingNumber);
        settings.add(companyBuildingNumberAsString);
        settings.add(companyCity);
        settings.add(companyCountry);
        
        for (String property : settings) {
            if (property.isBlank()) {
                return false;
            }
        }
        
        return true;
    }
}
