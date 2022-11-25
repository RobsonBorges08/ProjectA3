/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.domain.Supplier;
import com.budgetmanager.views.SupplierListView;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FilterSupplierTableHandler implements EventHandler<KeyEvent> {

    private final TextField searchField;
    private final TableView<Supplier> supplierTable;

    public FilterSupplierTableHandler(TextField searchField,
            TableView<Supplier> supplierTable) {
        this.searchField = searchField;
        this.supplierTable = supplierTable;
    }

    @Override
    public void handle(KeyEvent t) {
        String keywords;
        SupplierListView listView;
        List<Supplier> suppliersFound;
        ObservableList<Supplier> suppliersObservableList;

        keywords = searchField.getText();
        listView = new SupplierListView();
        suppliersFound = listView.filterByTradingName(keywords);
        suppliersObservableList = FXCollections
                .observableArrayList(suppliersFound);

        supplierTable.setItems(suppliersObservableList);
    }

}
