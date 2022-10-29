/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.handlers.SwitchToSettingsHandler;
import com.budgetmanager.application.interfaces.SceneFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BudgetRequestController extends SceneFactory {

    private double width = 640;
    private double height = 480;
    
    @FXML
    private Button switchToSettings;
    
    @FXML
    private ImageView magnifyingGlass1ImageView;
    
    @FXML
    private ImageView magnifyingGlass2ImageView;
    
    @FXML
    private ImageView gearImageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Class currentClass = getClass();
        
        URL magnifyingGlassURL = currentClass.getResource("magnifying-glass.png");
        String magnifyingGlassPathString = magnifyingGlassURL.toString();
        Image magnifyingGlassImage = new Image(magnifyingGlassPathString);
        magnifyingGlass1ImageView.setImage(magnifyingGlassImage);
        magnifyingGlass2ImageView.setImage(magnifyingGlassImage);
        
        URL gearURL = currentClass.getResource("gear.png");
        String gearPathString = gearURL.toString();
        Image gearImage = new Image(gearPathString);
        gearImageView.setImage(gearImage);
        
        EventHandler<ActionEvent> handler = new SwitchToSettingsHandler(currentClass);
        switchToSettings.setOnAction(handler);
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
