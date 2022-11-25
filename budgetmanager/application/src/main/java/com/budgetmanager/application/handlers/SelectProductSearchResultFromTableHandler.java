/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.application.ApplicationManager;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.ProductNotFoundException;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.ReadOnlyProduct;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class SelectProductSearchResultFromTableHandler implements
        EventHandler<MouseEvent> {

    private final TableView<ReadOnlyProduct> productSearchTable;
    private final UnitOfWork unitOfWork;
    private final Logger logger;

    public SelectProductSearchResultFromTableHandler(
            TableView<ReadOnlyProduct> resultTableView) {
        this.productSearchTable = resultTableView;
        this.unitOfWork = new UnitOfWork();
        
        Class currentClass = SelectProductSearchResultFromTableHandler.class;
        String className = currentClass.getName();
        this.logger = Logger.getLogger(className);
    }

    @Override
    public void handle(MouseEvent event) {
        try {
            TableView<ProductOnBudget> productsTable;
            MultipleSelectionModel selectionModel;
            ReadOnlyProduct selectedReadOnlyProduct;
            int productId;
            Product selectedProduct;
            ProductOnBudget productToRequest;

            selectionModel = productSearchTable.getSelectionModel();
            selectedReadOnlyProduct = (ReadOnlyProduct) selectionModel
                    .getSelectedItem();

            if (selectedReadOnlyProduct != null) {
                productsTable
                        = ApplicationManager.getProductsTable();
                productId = selectedReadOnlyProduct.getId();
                selectedProduct = unitOfWork.getProductById(productId);

                productToRequest = new ProductOnBudget();
                productToRequest.setProduct(selectedProduct);
                productToRequest.setQuantity(0);
                productToRequest.setUnitOfMeasure("un.");

                productsTable.getItems().add(productToRequest);

                Scene currentScene = productSearchTable.getScene();
                Stage currentStage = (Stage) currentScene.getWindow();
                currentStage.close();
            }
            
        } catch (ProductNotFoundException exception) {
            DialogService.showErrorMessage(exception);
            logger.log(Level.SEVERE, null, exception);
        }
    }

}
