package com.budgetmanager.application.interfaces;

import com.budgetmanager.application.controllers.SettingsController;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import com.budgetmanager.core.exceptions.InvalidSceneException;

public abstract class SceneFactory implements Initializable {
    
    private static String fxmlFileBaseName = "";
    private static double width = -1;
    private static double height = -1;
    
    private static void throwInvalidScene(String dataLabel) throws InvalidSceneException {
        throw new InvalidSceneException(dataLabel + " must be set");
    }
    
    private static void checkIfIsSet(String dataToCheck, String dataLabel) throws InvalidSceneException {
        if (dataToCheck.isEmpty()) {
            throwInvalidScene(dataLabel);
        }
    }
    
    private static void checkIfIsSet(double dataToCheck, String dataLabel) throws InvalidSceneException {
        if (dataToCheck < 0) {
            throwInvalidScene(dataLabel);
        }
    }

    public static Scene asScene() throws IOException, InvalidSceneException {
        checkIfIsSet(fxmlFileBaseName, "file base name");
        checkIfIsSet(width, "width");
        checkIfIsSet(height, "height");
        
        String fxmlFileName = fxmlFileBaseName + ".fxml";
        Class controllerClass = SettingsController.class;
        
        URL fxmlFilePath = controllerClass.getResource(fxmlFileName);
        FXMLLoader loader = new FXMLLoader(fxmlFilePath);
        Parent loadedFXML = loader.load();

        return new Scene(loadedFXML, width, height);
    }
}
