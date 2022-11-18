/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import com.budgetmanager.application.handlers.ConfirmCategoryDataHandler;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class CategoryCreationController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private Button createCategory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConfirmCategoryDataHandler handler;
        handler = new ConfirmCategoryDataHandler(nameField);
        createCategory.setOnAction(handler);
    }

}
