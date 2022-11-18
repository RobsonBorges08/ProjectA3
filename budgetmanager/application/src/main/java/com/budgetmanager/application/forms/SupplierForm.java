package com.budgetmanager.application.forms;

import com.budgetmanager.domain.Category;
import com.budgetmanager.domain.supplier.SupplierData;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.control.TextField;
import lombok.Data;

@Data
public class SupplierForm {

    private TextField companyNameField;
    private TextField tradingNameField;
    private TextField employersIdentificationNumberField;
    private TextField emailField;
    private TextField zipCodeField;
    private TextField streetField;
    private TextField buildingNumberField;
    private TextField cityField;
    private TextField countryField;
    private TextField categoriesField;

    public SupplierData toSupplierData() {
        SupplierData data = new SupplierData();

        String companyName = companyNameField.getText();
        data.setCompanyName(companyName);

        String tradingName = tradingNameField.getText();
        data.setTradingName(tradingName);

        String employersIdentificationNumber = employersIdentificationNumberField.getText();
        data.setEmployersIdentificationNumber(employersIdentificationNumber);

        String email = emailField.getText();
        data.setEmail(email);

        String zipCode = zipCodeField.getText();
        data.setZipCode(zipCode);

        String street = streetField.getText();
        data.setStreet(street);

        String buildingNumberAsString = buildingNumberField.getText();
        int buildingNumber = Integer.parseInt(buildingNumberAsString);
        data.setBuildingNumber(buildingNumber);

        String city = cityField.getText();
        data.setCity(city);

        String country = countryField.getText();
        data.setCountry(country);

        String categoriesAsString = categoriesField.getText();
        String categoriesArray[] = categoriesAsString.split(",");
        List<String> categoriesList = Arrays.asList(categoriesArray);
        Set<String> categories = new HashSet<>(categoriesList);
        data.setCategories(categories);

        return data;
    }
}
