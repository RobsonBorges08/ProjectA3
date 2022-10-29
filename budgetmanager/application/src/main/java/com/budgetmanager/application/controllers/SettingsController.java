/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.forms.SettingsForm;
import com.budgetmanager.application.handlers.CancelHandler;
import com.budgetmanager.application.interfaces.SceneFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import com.budgetmanager.application.handlers.ConfirmHandler;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingsController extends SceneFactory {

    private double width = 640;
    private double height = 480;
    
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField occupationField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField companyNameField;
    @FXML
    private TextField companyPhoneField;
    @FXML
    private TextField companyStreetField;
    @FXML
    private TextField companyBuildingNumberField;
    @FXML
    private TextField companyCityField;
    @FXML
    private TextField companyCountryField;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SettingsForm settingsForm = new SettingsForm();
        settingsForm.setFullNameField(fullNameField);
        settingsForm.setOccupationField(occupationField);
        settingsForm.setEmailField(emailField);
        settingsForm.setCompanyNameField(companyNameField);
        settingsForm.setCompanyPhoneField(companyPhoneField);
        settingsForm.setCompanyStreetField(companyStreetField);
        settingsForm.setCompanyBuildingNumberField(companyBuildingNumberField);
        settingsForm.setCompanyCityField(companyCityField);
        settingsForm.setCompanyCountryField(companyCountryField);
        
        EventHandler<ActionEvent> confirmHandler;
        EventHandler<ActionEvent> cancelHandler;
        try {
            confirmHandler = new ConfirmHandler(SettingsController.class, settingsForm);
            confirmButton.setOnAction(confirmHandler);
            
            cancelHandler = new CancelHandler(SettingsController.class);
            cancelButton.setOnAction(cancelHandler);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public Class getControllerClass() {
        return getClass();
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

}
