package com.budgetmanager.application.services;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogService {
    public static void showErrorMessage(String errorMessage) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("ERROR");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(errorMessage);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
