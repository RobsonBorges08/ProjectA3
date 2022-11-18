/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.forms.ProductForm;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.CategoryNotFoundException;
import com.budgetmanager.core.exceptions.InvalidProductException;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.product.ProductData;
import com.budgetmanager.handlers.CreateProductHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class ConfirmProductDataHandler implements EventHandler<ActionEvent> {

    private final ProductForm productForm;

    public ConfirmProductDataHandler(ProductForm productForm) {
        this.productForm = productForm;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            ProductData productData = productForm.toProductData();
            CreateProductHandler handler = new CreateProductHandler(productData);
            Product product = handler.handle(event);

            String successMessage = String.format("Cadastrado produto %d",
                    product.getId());
            DialogService.showSuccessMessage(successMessage);
            
            TextField descriptionField = productForm.getDescriptionField();
            Scene currentScene = descriptionField.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();

        } catch (CategoryNotFoundException | InvalidProductException exception) {
            DialogService.showErrorMessage(exception.getCause().toString());
            Logger.getLogger(ConfirmProductDataHandler.class.getName())
                    .log(Level.SEVERE, null, exception);

        }
    }

}
