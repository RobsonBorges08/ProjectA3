/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.App;
import com.budgetmanager.application.forms.SettingsForm;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.InvalidSettingsException;
import com.budgetmanager.domain.Settings;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import com.budgetmanager.services.SettingsService;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfirmHandler implements EventHandler<ActionEvent> {

    private final URL budgetRequestScreenFxmlURL;
    private final SettingsForm settingsForm;

    public ConfirmHandler(Class controllerClass, SettingsForm settingsForm)
            throws IOException {
        this.budgetRequestScreenFxmlURL = controllerClass.getResource(
                "budgetrequest.fxml");
        this.settingsForm = settingsForm;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            SettingsService settingsService = new SettingsService();

            TextField fullNameField = settingsForm.getFullNameField();
            TextField occupationField = settingsForm.getOccupationField();
            TextField emailField = settingsForm.getEmailField();
            TextField companyNameField = settingsForm.getCompanyNameField();
            TextField companyPhoneField = settingsForm.getCompanyPhoneField();
            TextField companyStreetField = settingsForm.getCompanyStreetField();
            TextField companyBuildingNumberField = settingsForm
                    .getCompanyBuildingNumberField();
            TextField companyCityField = settingsForm.getCompanyCityField();
            TextField companyCountryField = settingsForm
                    .getCompanyCountryField();

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

            Settings newSettings = new Settings();
            newSettings.setFullName(fullName);
            newSettings.setOccupation(occupation);
            newSettings.setEmail(email);
            newSettings.setCompanyName(companyName);
            newSettings.setCompanyPhone(companyPhone);
            newSettings.setCompanyStreet(companyStreet);
            newSettings.setCompanyBuildingNumber(companyBuildingNumber);
            newSettings.setCompanyCity(companyCity);
            newSettings.setCompanyCountry(companyCountry);

            settingsService.updateSettings(newSettings);

            FXMLLoader loader = new FXMLLoader(budgetRequestScreenFxmlURL);
            Parent loadedFXML = loader.load();
            App.setRoot(loadedFXML);

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

}
