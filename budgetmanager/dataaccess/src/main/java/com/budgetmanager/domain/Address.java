package com.budgetmanager.domain;

import lombok.Data;

@Data
public class Address {
    private String zipCode;
    private String street;
    private int buildingNumber;
    private String city;
    private String country;
}
