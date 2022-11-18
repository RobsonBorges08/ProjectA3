/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.ApplicationManager;
import com.budgetmanager.application.forms.SupplierForm;
import com.budgetmanager.application.handlers.CallCategoryCreationHandler;
import com.budgetmanager.application.handlers.CallCategorySearchHandler;
import com.budgetmanager.application.handlers.ConfirmSupplierDataHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SupplierCreationController implements Initializable {

    @FXML private Button callCategoryCreation;
    @FXML private Button confirmButton;
    @FXML private Button searchCategoryButton;
    @FXML private TextField buildingNumberField;
    @FXML private TextField categoriesField;
    @FXML private TextField cityField;
    @FXML private TextField companyNameField;
    @FXML private TextField countryField;
    @FXML private TextField emailField;
    @FXML private TextField einField;
    @FXML private TextField streetField;
    @FXML private TextField tradingNameField;
    @FXML private TextField zipCodeField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ApplicationManager.setSupplierCreationCategoriesField(categoriesField);

        EventHandler<ActionEvent> callCategoryCreationHandler;
        EventHandler<ActionEvent> callCategorySearchHandler;
        EventHandler<ActionEvent> confirmDataHandler;

        callCategoryCreationHandler = new CallCategoryCreationHandler();
        callCategoryCreation.setOnAction(callCategoryCreationHandler);

        callCategorySearchHandler = new CallCategorySearchHandler();
        searchCategoryButton.setOnAction(callCategorySearchHandler);

        SupplierForm supplierForm = makeSupplierForm();
        confirmDataHandler = new ConfirmSupplierDataHandler(supplierForm);
        confirmButton.setOnAction(confirmDataHandler);
    }

    private SupplierForm makeSupplierForm() {
        SupplierForm form = new SupplierForm();

        form.setBuildingNumberField(buildingNumberField);
        form.setCategoriesField(categoriesField);
        form.setCityField(cityField);
        form.setCompanyNameField(companyNameField);
        form.setCountryField(countryField);
        form.setEmailField(emailField);
        form.setEmployersIdentificationNumberField(einField);
        form.setStreetField(streetField);
        form.setTradingNameField(tradingNameField);
        form.setZipCodeField(zipCodeField);

        return form;
    }

}
