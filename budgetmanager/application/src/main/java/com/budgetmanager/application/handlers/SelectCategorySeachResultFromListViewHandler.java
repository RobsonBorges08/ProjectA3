/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.ApplicationManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class SelectCategorySeachResultFromListViewHandler implements
        EventHandler<MouseEvent> {

    private ListView<String> resultListView;

    public SelectCategorySeachResultFromListViewHandler(
            ListView<String> resultListView) {
        this.resultListView = resultListView;
    }

    @Override
    public void handle(MouseEvent event) {
        TextField categoriesField;
        MultipleSelectionModel selectionModel;

        selectionModel = resultListView.getSelectionModel();
        String selectedCategory = (String) selectionModel.getSelectedItem();

        categoriesField
                = ApplicationManager.getSupplierCreationCategoriesField();
        String currentValue = categoriesField.getText();

        if (selectedCategory != null) {
            String finalValue = joinCategory(currentValue, selectedCategory);
            categoriesField.setText(finalValue);

            Scene currentScene = resultListView.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();
        }
    }

    private String joinCategory(String currentValue, String selectedCategory) {
        if (currentValue.isBlank()) {
            return selectedCategory;
        }

        return currentValue + "," + selectedCategory;
    }

}
