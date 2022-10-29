package com.budgetmanager.application.forms;

import javafx.scene.control.TextField;
import lombok.Data;

@Data
public class SettingsForm {
    private TextField fullNameField;
    private TextField occupationField;
    private TextField emailField;
    private TextField companyNameField;
    private TextField companyPhoneField;
    private TextField companyStreetField;
    private TextField companyBuildingNumberField;
    private TextField companyCityField;
    private TextField companyCountryField;
}
