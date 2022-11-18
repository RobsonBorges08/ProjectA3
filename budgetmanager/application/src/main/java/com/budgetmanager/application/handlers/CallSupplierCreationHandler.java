/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.controllers.SupplierCreationController;
import com.budgetmanager.application.services.DialogService;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class CallSupplierCreationHandler implements EventHandler<ActionEvent> {

    private final URL supplierCreationFxmlPath;
    private final Stage supplierCreationPopUp;
    private final Logger logger;

    public CallSupplierCreationHandler() {
        Class controllerClass = SupplierCreationController.class;
        this.supplierCreationFxmlPath = controllerClass.getResource(
                "suppliercreation.fxml");

        this.supplierCreationPopUp = new Stage();
        this.logger = Logger.getLogger(CallSupplierCreationHandler.class
                .getName());
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(supplierCreationFxmlPath);
            Parent loadedFXML = loader.load();

            Scene productCreationScene = new Scene(loadedFXML);
            supplierCreationPopUp.setScene(productCreationScene);
            supplierCreationPopUp.show();

        } catch (IOException ex) {
            DialogService.showErrorMessage(ex.getCause().toString());
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
