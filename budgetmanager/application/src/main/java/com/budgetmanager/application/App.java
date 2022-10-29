package com.budgetmanager.application;

import com.budgetmanager.application.controllers.SettingsController;
import com.budgetmanager.core.exceptions.InvalidSceneException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, InvalidSceneException {
        SettingsController controller = new SettingsController();
        scene = controller.asScene();
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