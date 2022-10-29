/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.App;
import java.io.IOException;
import java.net.URL;
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
        this.budgetRequestScreenFxmlURL = controllerClass.getResource("budgetrequest.fxml");
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(budgetRequestScreenFxmlURL);
            Parent loadedFXML = loader.load();
            App.setRoot(loadedFXML);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
