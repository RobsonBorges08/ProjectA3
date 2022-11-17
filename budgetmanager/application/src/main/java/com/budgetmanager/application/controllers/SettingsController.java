/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.forms.SettingsForm;
import com.budgetmanager.application.handlers.CancelHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import com.budgetmanager.application.handlers.ConfirmHandler;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.InvalidSettingsException;
import com.budgetmanager.domain.Settings;
import com.budgetmanager.services.SettingsService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SettingsController implements Initializable {

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
        try {
            loadSettings();
            setHandlers();

        } catch (IOException | URISyntaxException | InvalidSettingsException ex) {
            DialogService.showErrorMessage(ex);
            Logger.getLogger(ConfirmHandler.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (NumberFormatException ex) {
            DialogService.showErrorMessage("Número do prédio invalido");
            Logger.getLogger(ConfirmHandler.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private void loadSettings() throws URISyntaxException, IOException,
            InvalidSettingsException {
        SettingsService settingsService = new SettingsService();

        if (settingsService.isApplicationConfigured()) {
            Settings settings = settingsService.getSettings();

            String fullName = settings.getFullName();
            fullNameField.setText(fullName);

            String occupation = settings.getOccupation();
            occupationField.setText(occupation);

            String email = settings.getEmail();
            emailField.setText(email);

            String companyName = settings.getCompanyName();
            companyNameField.setText(companyName);

            String companyPhone = settings.getCompanyPhone();
            companyPhoneField.setText(companyPhone);

            String companyStreet = settings.getCompanyStreet();
            companyStreetField.setText(companyStreet);

            int companyBuildingNumberAsInteger;
            companyBuildingNumberAsInteger = settings.getCompanyBuildingNumber();
            String companyBuildingNumber = String.valueOf(
                    companyBuildingNumberAsInteger);
            companyBuildingNumberField.setText(companyBuildingNumber);

            String companyCity = settings.getCompanyCity();
            companyCityField.setText(companyCity);

            String companyCountry = settings.getCompanyCountry();
            companyCountryField.setText(companyCountry);
        }
    }

    private void setHandlers() throws IOException {
        SettingsForm settingsForm = new SettingsForm();
        settingsForm.setFullNameField(fullNameField);
        settingsForm.setOccupationField(occupationField);
        settingsForm.setEmailField(emailField);
        settingsForm.setCompanyNameField(companyNameField);
        settingsForm.setCompanyPhoneField(companyPhoneField);
        settingsForm.setCompanyStreetField(companyStreetField);
        settingsForm.setCompanyBuildingNumberField(
                companyBuildingNumberField);
        settingsForm.setCompanyCityField(companyCityField);
        settingsForm.setCompanyCountryField(companyCountryField);

        EventHandler<ActionEvent> confirmHandler;
        EventHandler<ActionEvent> cancelHandler;
        confirmHandler = new ConfirmHandler(SettingsController.class,
                settingsForm);
        confirmButton.setOnAction(confirmHandler);

        cancelHandler = new CancelHandler(SettingsController.class);
        cancelButton.setOnAction(cancelHandler);
    }
}
