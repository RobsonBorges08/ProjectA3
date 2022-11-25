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

public class CallProductSearchHandler implements EventHandler<ActionEvent> {

    private final URL productSearchFxmlPath;
    private final Stage productSearchPopUp;

    public CallProductSearchHandler() {
        Class controllerClass = ProductSearchController.class;
        this.productSearchFxmlPath = controllerClass.getResource(
                "productsearch.fxml");

        this.productSearchPopUp = new Stage();
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(productSearchFxmlPath);
            Parent loadedFXML = loader.load();

            Scene productSearchScene = new Scene(loadedFXML);
            productSearchPopUp.setScene(productSearchScene);
            productSearchPopUp.show();

        } catch (IOException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(CallProductSearchHandler.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
    }

}
