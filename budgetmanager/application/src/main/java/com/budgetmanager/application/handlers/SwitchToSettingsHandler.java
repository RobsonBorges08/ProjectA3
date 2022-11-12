/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.App;
import com.budgetmanager.application.services.DialogService;
import java.io.IOException;
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
public class SwitchToSettingsHandler implements EventHandler<ActionEvent> {

    private URL settingsFXMLURL;
    
    public SwitchToSettingsHandler(Class controllerClass){
        settingsFXMLURL = controllerClass.getResource("settings.fxml");
    }
            
    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(settingsFXMLURL);
            Parent loadedFXML = loader.load();
            App.setRoot(loadedFXML);
        } catch (IOException ex) {
            DialogService.showErrorMessage(ex);
            Logger.getLogger(CallProductCreationHandler.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
