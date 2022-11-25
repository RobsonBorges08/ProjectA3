/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.controllers;

import com.budgetmanager.application.handlers.FilterSupplierTableHandler;
import com.budgetmanager.application.handlers.SelectSupplierSearchResultFromTableHandler;
import com.budgetmanager.domain.Supplier;
import com.budgetmanager.views.SupplierListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class SupplierSearchController implements Initializable {

    @FXML private TextField searchField;
    @FXML private TableView<Supplier> supplierSearchTable;
    @FXML private TableColumn companyNameColumn;
    @FXML private TableColumn tradingNameColumn;
    @FXML private TableColumn cityColumn;
    @FXML private TableColumn idColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SupplierListView listView;
        List<Supplier> supplierList;
        ObservableList<Supplier> supplierObservableList;

        listView = new SupplierListView();
        supplierList = listView.list();
        supplierObservableList = FXCollections.observableArrayList(supplierList);
        supplierSearchTable.setItems(supplierObservableList);

        Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>> companyNameCellFactory;
        companyNameCellFactory = new PropertyValueFactory<>("companyName");
        companyNameColumn.setCellValueFactory(companyNameCellFactory);

        Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>> tradingNameCellFactory;
        tradingNameCellFactory = new PropertyValueFactory<>("tradingName");
        tradingNameColumn.setCellValueFactory(tradingNameCellFactory);

        Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>> cityCellFactory;
        cityCellFactory = new PropertyValueFactory<>("city");
        cityColumn.setCellValueFactory(cityCellFactory);

        Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>> idCellFactory;
        idCellFactory = new PropertyValueFactory<>("id");
        idColumn.setCellValueFactory(idCellFactory);

        EventHandler<KeyEvent> searchFieldHandler;
        searchFieldHandler = new FilterSupplierTableHandler(searchField,
                supplierSearchTable);
        searchField.setOnKeyTyped(searchFieldHandler);
        
        EventHandler<MouseEvent> selectSupplierHandler;
        selectSupplierHandler = new SelectSupplierSearchResultFromTableHandler(supplierSearchTable);
        supplierSearchTable.setOnMouseClicked(selectSupplierHandler);
    }

}
