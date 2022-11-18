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
import com.budgetmanager.services.SettingsService;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfirmNewSettingsHandler implements EventHandler<ActionEvent> {

    private final URL budgetRequestScreenFxmlURL;
    private final SettingsForm settingsForm;

    public ConfirmNewSettingsHandler(Class controllerClass, SettingsForm settingsForm)
            throws IOException {
        this.budgetRequestScreenFxmlURL = controllerClass.getResource(
                "budgetrequest.fxml");
        this.settingsForm = settingsForm;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            SettingsService settingsService = new SettingsService();
            
            Settings newSettings = settingsForm.toSettings();
            settingsService.updateSettings(newSettings);

            FXMLLoader loader = new FXMLLoader(budgetRequestScreenFxmlURL);
            Parent loadedFXML = loader.load();
            App.setRoot(loadedFXML);

        } catch (IOException | URISyntaxException | InvalidSettingsException ex) {
            DialogService.showErrorMessage(ex);
            Logger.getLogger(ConfirmNewSettingsHandler.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (NumberFormatException ex) {
            DialogService.showErrorMessage("Número do prédio invalido");
            Logger.getLogger(ConfirmNewSettingsHandler.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

}
