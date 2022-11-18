package com.budgetmanager.application.handlers;

import com.budgetmanager.application.controllers.CategorySearchController;
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

public class CallCategorySearchHandler implements EventHandler<ActionEvent> {

    private final URL categorySearchFxmlPath;
    private final Stage categorySearchPopUp;

    public CallCategorySearchHandler() {
        Class controllerClass = CategorySearchController.class;
        this.categorySearchFxmlPath = controllerClass.getResource(
                "categorysearch.fxml");

        this.categorySearchPopUp = new Stage();
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(categorySearchFxmlPath);
            Parent loadedFXML = loader.load();

            Scene categorySearchScene = new Scene(loadedFXML);
            categorySearchPopUp.setScene(categorySearchScene);
            categorySearchPopUp.show();

        } catch (IOException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(CallCategorySearchHandler.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
    }

}
