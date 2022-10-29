/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.interfaces.SceneFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import com.budgetmanager.application.handlers.ConfirmHandler;
import java.io.IOException;
import javafx.fxml.FXML;

public class SettingsController extends SceneFactory {

    private double width = 640;
    private double height = 480;
    
    @FXML
    private Button confirmButton;
    
    @FXML
    private Button cancelButton;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventHandler<ActionEvent> eventHandler;
        try {
            eventHandler = new ConfirmHandler(SettingsController.class);
            confirmButton.setOnAction(eventHandler);
            cancelButton.setOnAction(eventHandler);
            
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
