package com.budgetmanager.domain;

import lombok.Data;

@Data
public class Company {
    private String name;
    private String phone;
    private Address address;
}
