package com.budgetmanager.application.handlers;

import com.budgetmanager.application.controllers.ProductCreationController;
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

public class CallProductCreationHandler implements EventHandler<ActionEvent> {

    private final URL productCreationFxmlPath;
    private final Stage productCreationPopUp;

    public CallProductCreationHandler() {
        Class controllerClass = ProductCreationController.class;
        this.productCreationFxmlPath = controllerClass.getResource(
                "productcreation.fxml");

        this.productCreationPopUp = new Stage();
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(productCreationFxmlPath);
            Parent loadedFXML = loader.load();

            Scene productCreationScene = new Scene(loadedFXML);
            productCreationPopUp.setScene(productCreationScene);
            productCreationPopUp.show();

        } catch (IOException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(CallProductCreationHandler.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
    }

}
