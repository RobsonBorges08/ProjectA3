/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.domain.ReadOnlyProduct;
import com.budgetmanager.views.ProductListView;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FilterProductsTableHandler implements EventHandler<KeyEvent> {

    private final TextField searchField;
    private final TableView<ReadOnlyProduct> productTable;

    public FilterProductsTableHandler(TextField searchField,
            TableView<ReadOnlyProduct> productTable) {
        this.searchField = searchField;
        this.productTable = productTable;
    }

    @Override
    public void handle(KeyEvent t) {
        String keywords;
        ProductListView listView;
        List<ReadOnlyProduct> productsFound;
        ObservableList<ReadOnlyProduct> productsObservableList;

        keywords = searchField.getText();
        listView = new ProductListView();
        productsFound = listView.filterByDescription(keywords);
        productsObservableList = FXCollections
                .observableArrayList(productsFound);

        productTable.setItems(productsObservableList);
    }

}
