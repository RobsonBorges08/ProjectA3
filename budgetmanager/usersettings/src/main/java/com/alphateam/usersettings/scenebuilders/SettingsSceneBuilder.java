package com.alphateam.usersettings.scenebuilders;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class SettingsSceneBuilder {
    
    private Parent loadedSettingsFXML;
    private double width;
    private double height;
    
    public SettingsSceneBuilder(double d, double d1) {
        try {
            this.loadedSettingsFXML = loadFXML();
        } catch (IOException e) {
            popupErrorDialog(e.getMessage());
        }
    }
    
    public void setSize(double width, double height){
        this.width = width;
        this.height = height;
    }
    
    public Scene build() {
        return new Scene(loadedSettingsFXML, width, height);
    }
    
    private Parent loadFXML() throws IOException {
        Class builderClass = SettingsSceneBuilder.class;
        URL settingsFXML = builderClass.getResource("settings.fxml");
        FXMLLoader loader = new FXMLLoader(settingsFXML);
        
        return loader.load();
    }
    
    private void popupErrorDialog(String message) {
      Dialog<String> dialog = new Dialog<>();
      ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
      
      dialog.setTitle("Error");
      dialog.setContentText(message);
      dialog.getDialogPane().getButtonTypes().add(type);
      
      dialog.showAndWait();
    }
    
}
