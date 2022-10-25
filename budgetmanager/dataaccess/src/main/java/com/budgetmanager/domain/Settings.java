package com.budgetmanager.domain;

import lombok.Data;

@Data
public class Settings {
    private String fullName;
    private String occupation;
    private String email;
    private Company company;
}
