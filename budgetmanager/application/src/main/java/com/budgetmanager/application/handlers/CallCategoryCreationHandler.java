package com.budgetmanager.application.handlers;

import com.budgetmanager.application.controllers.CategoryCreationController;
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

public class CallCategoryCreationHandler implements EventHandler<ActionEvent> {

    private final URL categoryCreationFxmlPath;
    private final Stage categoryCreationPopUp;

    public CallCategoryCreationHandler() {
        Class controllerClass = CategoryCreationController.class;
        this.categoryCreationFxmlPath = controllerClass.getResource(
                "categorycreation.fxml");

        this.categoryCreationPopUp = new Stage();
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            FXMLLoader loader = new FXMLLoader(categoryCreationFxmlPath);
            Parent loadedFXML = loader.load();

            Scene categoryCreationScene = new Scene(loadedFXML);
            categoryCreationPopUp.setScene(categoryCreationScene);
            categoryCreationPopUp.show();

        } catch (IOException ex) {
            DialogService.showErrorMessage(ex.getCause().toString());
            Logger.getLogger(CallCategoryCreationHandler.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
