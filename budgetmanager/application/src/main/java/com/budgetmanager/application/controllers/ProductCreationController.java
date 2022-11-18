/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.forms.ProductForm;
import com.budgetmanager.application.handlers.CallCategoryCreationHandler;
import com.budgetmanager.application.handlers.ConfirmProductDataHandler;
import com.budgetmanager.application.handlers.FilterCategoryComboBoxHandler;
import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.domain.Category;
import com.budgetmanager.views.CategoryListView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static com.budgetmanager.views.CategoryListView.getCategoryNameList;

public class ProductCreationController implements Initializable {

    @FXML
    private Button callCategoryCreation;

    @FXML
    private ComboBox<String> categoryComboBox;
    
    @FXML
    private Button createProduct;
    
    @FXML
    private TextField descriptionField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try ( CategoryListView listView = new CategoryListView()) {
            List<Category> categoryList;
            List<String> categoryNameList;
            ObservableList<String> categoryNameObservableList;

            categoryList = listView.list();
            categoryNameList = getCategoryNameList(categoryList);
            categoryNameObservableList = FXCollections.observableArrayList(
                    categoryNameList);

            categoryComboBox.setItems(categoryNameObservableList);

        } catch (IOException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(ProductCreationController.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        
        setHandlers();
    }
    
    private void setHandlers() {
        FilterCategoryComboBoxHandler onCategoryNameTypedHandler;
        ConfirmProductDataHandler confirmDataHandler;
        
        onCategoryNameTypedHandler = new FilterCategoryComboBoxHandler(
                categoryComboBox);
        TextField comboBoxEditor = categoryComboBox.getEditor();
        comboBoxEditor.setOnKeyTyped(onCategoryNameTypedHandler);

        CallCategoryCreationHandler handler = new CallCategoryCreationHandler();
        callCategoryCreation.setOnAction(handler);

        ProductForm productForm = makeProductForm();
        confirmDataHandler = new ConfirmProductDataHandler(productForm);
        createProduct.setOnAction(confirmDataHandler);
    }

    private ProductForm makeProductForm() {
        ProductForm form = new ProductForm();
        form.setDescriptionField(descriptionField);
        form.setCategoryComboBox(categoryComboBox);
        
        return form;
    }

}
