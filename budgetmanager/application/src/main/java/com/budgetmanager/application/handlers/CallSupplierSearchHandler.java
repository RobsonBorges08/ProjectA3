package com.budgetmanager.application.handlers;

import com.budgetmanager.application.controllers.ProductSearchController;
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

public class CallSupplierSearchHandler implements EventHandler<ActionEvent> {

    private final URL supplierSearchFxmlPath;
    private final Stage supplierSearchPopUp;

    public CallSupplierSearchHandler() {
        Class controllerClass = ProductSearchController.class;
        this.supplierSearchFxmlPath = controllerClass.getResource(
                "suppliersearch.fxml");

        this.supplierSearchPopUp = new Stage();
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(supplierSearchFxmlPath);
            Parent loadedFXML = loader.load();

            Scene supplierSearchScene = new Scene(loadedFXML);
            supplierSearchPopUp.setScene(supplierSearchScene);
            supplierSearchPopUp.show();

        } catch (IOException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(CallSupplierSearchHandler.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
    }

}
