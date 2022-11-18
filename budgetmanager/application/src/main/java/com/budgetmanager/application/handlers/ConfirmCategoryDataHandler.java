/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.InvalidCategoryException;
import com.budgetmanager.domain.Category;
import com.budgetmanager.handlers.CreateCategoryHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfirmCategoryDataHandler implements EventHandler<ActionEvent> {

    private TextField nameField;

    public ConfirmCategoryDataHandler(TextField nameField) {
        this.nameField = nameField;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String categoryName = nameField.getText();
            CreateCategoryHandler handler = new CreateCategoryHandler(
                    categoryName);
            Category category = handler.handle(event);

            int categoryId = category.getId();
            
            String successMessage = String
                    .format("Categoria %s(%d) registrada!",
                            categoryName,
                            categoryId);
            DialogService.showSuccessMessage(successMessage);

            Button sourceButton = (Button) event.getSource();
            Scene sourceScene = sourceButton.getScene();
            Stage sourceStage = (Stage) sourceScene.getWindow();
            sourceStage.close();

        } catch (InvalidCategoryException ex) {
            DialogService.showErrorMessage(ex);
            Logger.getLogger(ConfirmCategoryDataHandler.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
