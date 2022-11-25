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
import com.budgetmanager.domain.Supplier;
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
public class SelectSupplierSearchResultFromTableHandler implements
        EventHandler<MouseEvent> {

    private final TableView<Supplier> supplierSearchTable;
    private final UnitOfWork unitOfWork;
    private final Logger logger;

    public SelectSupplierSearchResultFromTableHandler(
            TableView<Supplier> resultTableView) {
        this.supplierSearchTable = resultTableView;
        this.unitOfWork = new UnitOfWork();

        Class currentClass = SelectSupplierSearchResultFromTableHandler.class;
        String className = currentClass.getName();
        this.logger = Logger.getLogger(className);
    }

    @Override
    public void handle(MouseEvent event) {
        TableView<Supplier> suppliersTable;
        MultipleSelectionModel selectionModel;
        Supplier selectedSupplier;

        selectionModel = supplierSearchTable.getSelectionModel();
        selectedSupplier = (Supplier) selectionModel
                .getSelectedItem();

        if (selectedSupplier != null) {
            suppliersTable
                    = ApplicationManager.getSuppliersTable();

            suppliersTable.getItems().add(selectedSupplier);

            Scene currentScene = supplierSearchTable.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();
        }
    }

}
