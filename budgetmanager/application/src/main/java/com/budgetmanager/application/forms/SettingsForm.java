package com.budgetmanager.application.forms;

import com.budgetmanager.domain.Settings;
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

    public Settings toSettings() {
        Settings settings = new Settings();
        
        String fullName = fullNameField.getText();
        String occupation = occupationField.getText();
        String email = emailField.getText();
        String companyName = companyNameField.getText();
        String companyPhone = companyPhoneField.getText();
        String companyStreet = companyStreetField.getText();
        String companyBuildingNumberAsString = companyBuildingNumberField
                .getText();
        int companyBuildingNumber = Integer.parseInt(
                companyBuildingNumberAsString);
        String companyCity = companyCityField.getText();
        String companyCountry = companyCountryField.getText();

        settings.setFullName(fullName);
        settings.setOccupation(occupation);
        settings.setEmail(email);
        settings.setCompanyName(companyName);
        settings.setCompanyPhone(companyPhone);
        settings.setCompanyStreet(companyStreet);
        settings.setCompanyBuildingNumber(companyBuildingNumber);
        settings.setCompanyCity(companyCity);
        settings.setCompanyCountry(companyCountry);

        return settings;
    }
}
