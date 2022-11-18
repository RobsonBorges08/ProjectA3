/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.application.controllers.ProductCreationController;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.domain.Category;
import com.budgetmanager.views.CategoryListView;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import static com.budgetmanager.views.CategoryListView.getCategoryNameList;

/**
 *
 * @author Lucas
 */
public class FilterCategoryListViewHandler implements EventHandler<KeyEvent> {

    private final ListView<String> categoryListView;
    private final TextField categorySearchField;

    public FilterCategoryListViewHandler(TextField categorySearchField,
            ListView<String> categoryComboBox) {
        this.categoryListView = categoryComboBox;
        this.categorySearchField = categorySearchField;
    }

    @Override
    public void handle(KeyEvent event) {
        try ( CategoryListView listView = new CategoryListView()) {
            List<Category> categoryList;
            List<String> categoryNameList;
            ObservableList<String> categoryNameObservableList;
            String keywords = categorySearchField.getText();

            categoryList = listView.filterByName(
                    keywords);
            categoryNameList = getCategoryNameList(categoryList);
            categoryNameObservableList = FXCollections.observableArrayList(
                    categoryNameList);

            categoryListView.setItems(categoryNameObservableList);

        } catch (IOException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(ProductCreationController.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
    }
}
