package com.budgetmanager.application.services;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogService {
    public static void showMessage(String title, String message) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
    
    public static void showErrorMessage(String errorMessage) {
        showMessage("ERRO", errorMessage);
    }
    
    public static void showErrorMessage(Exception exception) {
        Throwable cause = exception.getCause();
        String errorMessage = exception.getMessage();
                
        if (cause != null) {
            errorMessage = cause.toString();
        }
        
        showErrorMessage(errorMessage);
    }

    public static void showSuccessMessage(String successMessage) {
        showMessage("Sucesso!", successMessage);
    }
}
