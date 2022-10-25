package com.budgetmanager.application.interfaces;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import com.budgetmanager.core.exceptions.InvalidSceneException;

public abstract class SceneFactory implements Initializable {
    
    Class controllerClass = null;
    String fxmlFileName = "";
    double width = -1;
    double height = -1;

    public SceneFactory(Class controllerClass, double width, double height) {
        this.controllerClass = controllerClass;
        this.fxmlFileName = getFxmlFileNameForClass(controllerClass);
        this.width = width;
        this.height = height;
    }
    
    private static String getFxmlFileNameForClass(Class controllerClass) {
        String className = controllerClass.getSimpleName();
        int indexOfControllerWord = className.indexOf("Controller");
        String capitalizedFxmlBaseName = 
                className.substring(0, indexOfControllerWord);
        
        return capitalizedFxmlBaseName.toLowerCase() + ".fxml";
    }
    
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
        checkIfIsSet(controllerClass, "controller class");
        checkIfIsSet(fxmlFileName, "FXML file name");
        checkIfIsSet(width, "width");
        checkIfIsSet(height, "height");
        
        URL fxmlFilePath = controllerClass.getResource(fxmlFileName);
        FXMLLoader loader = new FXMLLoader(fxmlFilePath);
        Parent loadedFXML = loader.load();

        return new Scene(loadedFXML, width, height);
    }
}
