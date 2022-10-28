package com.budgetmanager.domain;

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
}
