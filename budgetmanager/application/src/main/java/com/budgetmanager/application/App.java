package com.budgetmanager.application;

import com.budgetmanager.application.controllers.SettingsController;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.InvalidSceneException;
import com.budgetmanager.services.SettingsService;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;

public class App extends Application {

    private static Scene scene;
    private static final double WIDTH = 640;
    private static final double HEIGHT = 480;

    @Override
    public void start(Stage stage) throws IOException, InvalidSceneException {
        String fxmlFileName = "settings.fxml";

        try {
            SettingsService settingsService = new SettingsService();
            if (settingsService.isApplicationConfigured()) {
                fxmlFileName = "budgetrequest.fxml";
            }
        } catch (URISyntaxException | FileNotFoundException ex) {
            DialogService.showErrorMessage(ex);
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        Class controllerClass = SettingsController.class;

        URL fxmlFilePath = controllerClass.getResource(fxmlFileName);
        FXMLLoader loader = new FXMLLoader(fxmlFilePath);
        Parent loadedFXML = loader.load();

        scene = new Scene(loadedFXML, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(Parent loadedFXML) throws IOException {
        scene.setRoot(loadedFXML);
    }

    public static void main(String[] args) {
        launch();
    }

}
