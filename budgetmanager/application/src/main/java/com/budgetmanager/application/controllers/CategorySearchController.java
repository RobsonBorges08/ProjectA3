/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.handlers.FilterCategoryListViewHandler;
import com.budgetmanager.application.handlers.SelectCategorySeachResultFromListViewHandler;
import com.budgetmanager.domain.Category;
import com.budgetmanager.views.CategoryListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import static com.budgetmanager.views.CategoryListView.getCategoryNameList;

public class CategorySearchController implements Initializable {

    @FXML
    private ListView<String> resultListView;

    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventHandler<KeyEvent> searchHandler;
        EventHandler<MouseEvent> selectCategoryHandler;
        List<Category> categoryList;
        List<String> resultList;
        ObservableList<String> resultObservableList;

        CategoryListView listView = new CategoryListView();
        categoryList = listView.list();
        resultList = getCategoryNameList(categoryList);
        resultObservableList = FXCollections.observableArrayList(resultList);
        resultListView.setItems(resultObservableList);

        searchHandler = new FilterCategoryListViewHandler(searchField,
                resultListView);
        searchField.setOnKeyTyped(searchHandler);

        selectCategoryHandler
                = new SelectCategorySeachResultFromListViewHandler(
                        resultListView);
        resultListView.setOnMouseClicked(selectCategoryHandler);
    }

}
