package com.budgetmanager.application.interfaces;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import com.budgetmanager.core.exceptions.InvalidSceneException;

public abstract class SceneFactory implements Initializable {
    
    Class CONTROLLER_CLASS = null;
    String FXML_BASE_NAME = "";
    double WIDTH = -1;
    double HEIGHT = -1;
    
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
    
    private static void checkIfIsSet(Class dataToCheck, String dataLabel) throws InvalidSceneException {
        if (dataToCheck == null) {
            throwInvalidScene(dataLabel);
        }
    }

    public Scene asScene() throws IOException, InvalidSceneException {
        checkIfIsSet(CONTROLLER_CLASS, "controller class");
        checkIfIsSet(FXML_BASE_NAME, "file base name");
        checkIfIsSet(WIDTH, "width");
        checkIfIsSet(HEIGHT, "height");
        
        String fxmlFileName = FXML_BASE_NAME + ".fxml";
        
        URL fxmlFilePath = CONTROLLER_CLASS.getResource(fxmlFileName);
        FXMLLoader loader = new FXMLLoader(fxmlFilePath);
        Parent loadedFXML = loader.load();

        return new Scene(loadedFXML, WIDTH, HEIGHT);
    }
}
