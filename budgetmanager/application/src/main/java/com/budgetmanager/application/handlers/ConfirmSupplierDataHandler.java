package com.budgetmanager.application.handlers;

import com.budgetmanager.application.forms.SupplierForm;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.InvalidSupplierException;
import com.budgetmanager.domain.Supplier;
import com.budgetmanager.domain.supplier.SupplierData;
import com.budgetmanager.handlers.CreateSupplierHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfirmSupplierDataHandler implements EventHandler<ActionEvent> {

    private SupplierForm supplierForm;

    public ConfirmSupplierDataHandler(SupplierForm supplierForm) {
        this.supplierForm = supplierForm;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            CreateSupplierHandler handler;
            SupplierData supplierData = supplierForm.toSupplierData();

            handler = new CreateSupplierHandler(supplierData);
            Supplier newSupplier = handler.handle(event);

            int supplierId = newSupplier.getId();
            String supplierName = newSupplier.getTradingName();
            String successMessage = String.format("Fornecedor %s(%d) criado!",
                    supplierName, supplierId);
            DialogService.showSuccessMessage(successMessage);
            
            TextField descriptionField = supplierForm.getCompanyNameField();
            Scene currentScene = descriptionField.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();

        } catch (InvalidSupplierException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(ConfirmSupplierDataHandler.class.getName())
                    .log(Level.SEVERE, null, exception);

        }
    }

}
