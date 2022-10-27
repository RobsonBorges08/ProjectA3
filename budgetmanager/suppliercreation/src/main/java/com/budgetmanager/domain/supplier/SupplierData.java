package com.budgetmanager.domain.supplier;

import java.util.Set;
import lombok.Data;

@Data
public class SupplierData {
    private String companyName;
    private String tradingName;
    private String employersIdentificationNumber;
    private String email;
    private String zipCode;
    private String street;
    private int buildingNumber;
    private String city;
    private String country;
    private Set<String> categories;
}
