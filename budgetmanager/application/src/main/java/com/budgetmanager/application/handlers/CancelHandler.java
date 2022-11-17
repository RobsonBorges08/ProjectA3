/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.App;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.services.SettingsService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Cliente
 */
public class CancelHandler implements EventHandler<ActionEvent> {

    private final URL budgetRequestScreenFxmlURL;

    public CancelHandler(Class controllerClass) throws IOException {
        this.budgetRequestScreenFxmlURL = controllerClass.getResource(
                "budgetrequest.fxml");
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            SettingsService settingsService = new SettingsService();
            if (settingsService.isApplicationConfigured()) {
                FXMLLoader loader = new FXMLLoader(budgetRequestScreenFxmlURL);
                Parent loadedFXML = loader.load();
                App.setRoot(loadedFXML);
            } else {
                DialogService.showErrorMessage("Aplicação não configurada");
            }

        } catch (IOException | URISyntaxException ex) {
            DialogService.showErrorMessage(ex);
            Logger.getLogger(CancelHandler.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (NumberFormatException ex) {
            DialogService.showErrorMessage("Aplicação não configurada");
            Logger.getLogger(CancelHandler.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

}
