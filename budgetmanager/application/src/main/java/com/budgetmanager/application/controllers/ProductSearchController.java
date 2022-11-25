package com.budgetmanager.application.controllers;

import com.budgetmanager.application.ApplicationManager;
import com.budgetmanager.application.handlers.FilterProductsTableHandler;
import com.budgetmanager.application.handlers.SelectProductSearchResultFromTableHandler;
import com.budgetmanager.domain.ReadOnlyProduct;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.budgetmanager.views.ProductListView;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ProductSearchController implements Initializable {

    @FXML private TextField searchField;
    @FXML private TableView<ReadOnlyProduct> productSearchTable;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn descriptionColumn;
    @FXML private TableColumn categoryColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PropertyValueFactory idFactory;
        PropertyValueFactory descriptionFactory;
        PropertyValueFactory categoryFactory;
        ProductListView listView;
        List<ReadOnlyProduct> productList;
        ObservableList<ReadOnlyProduct> productObservableList;
        EventHandler<KeyEvent> searchFieldHandler;

        idFactory = new PropertyValueFactory<>("id");
        descriptionFactory = new PropertyValueFactory<>("description");
        categoryFactory = new PropertyValueFactory<>("category");

        idColumn.setCellValueFactory(idFactory);
        descriptionColumn.setCellValueFactory(descriptionFactory);
        categoryColumn.setCellValueFactory(categoryFactory);

        listView = new ProductListView();
        productList = listView.list();
        productObservableList = FXCollections.observableArrayList(productList);
        productSearchTable.setItems(productObservableList);

        searchFieldHandler = new FilterProductsTableHandler(searchField,
                productSearchTable);
        searchField.setOnKeyTyped(searchFieldHandler);

        EventHandler<MouseEvent> selectProductHandler;
        selectProductHandler = new SelectProductSearchResultFromTableHandler(
                productSearchTable);
        productSearchTable.setOnMouseClicked(selectProductHandler);
    }

}
