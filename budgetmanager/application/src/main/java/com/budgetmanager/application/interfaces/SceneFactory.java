package com.budgetmanager.application.interfaces;

import com.budgetmanager.application.controllers.SettingsController;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public abstract class SceneFactory implements Initializable {
    
    private static String fxmlFileBaseName;
    private static double width;
    private static double height;

    public static Scene asScene() throws IOException {
        String fxmlFileName = fxmlFileBaseName + ".fxml";
        Class controllerClass = SettingsController.class;
        
        URL fxmlFilePath = controllerClass.getResource(fxmlFileName);
        FXMLLoader loader = new FXMLLoader(fxmlFilePath);
        Parent loadedFXML = loader.load();

        return new Scene(loadedFXML, width, height);
    }
}
