package com.budgetmanager.application.controllers;

import com.budgetmanager.application.ApplicationManager;
import com.budgetmanager.application.handlers.*;
import com.budgetmanager.application.interfaces.OnTableCellEditListener;
import com.budgetmanager.application.services.*;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class BudgetRequestController implements Initializable {

    @FXML private Button switchToSettings;
    @FXML private Button callProductSearch;
    @FXML private Button callSupplierSearch;
    @FXML private Button callProductCreation;
    @FXML private Button callSupplierCreation;
    @FXML private Button sendRequest;
    @FXML private ImageView gearImageView;
    @FXML private TextField observationsField;

    @FXML private TableView<ProductOnBudget> productsTable;
    @FXML private TableColumn productColumn;
    @FXML private TableColumn quantityColumn;
    @FXML private TableColumn unitOfMeasureColumn;
    @FXML private TableColumn removeProductColumn;

    @FXML private TableView<Supplier> suppliersTable;
    @FXML private TableColumn tradingNameColumn;
    @FXML private TableColumn companyNameColumn;
    @FXML private TableColumn einColumn;
    @FXML private TableColumn removeSupplierColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Class currentClass = getClass();

        ApplicationManager.setProductsTable(productsTable);
        ApplicationManager.setSuppliersTable(suppliersTable);

        URL gearURL = currentClass.getResource("gear.png");
        String gearPathString = gearURL.toString();
        Image gearImage = new Image(gearPathString);
        gearImageView.setImage(gearImage);

        EventHandler<ActionEvent> switchToSettingsHandler;
        switchToSettingsHandler = new SwitchToSettingsHandler(currentClass);
        switchToSettings.setOnAction(switchToSettingsHandler);

        EventHandler<ActionEvent> callProductSearchHandler;
        callProductSearchHandler = new CallProductSearchHandler();
        callProductSearch.setOnAction(callProductSearchHandler);

        EventHandler<ActionEvent> callProductCreationHandler;
        callProductCreationHandler = new CallProductCreationHandler();
        callProductCreation.setOnAction(callProductCreationHandler);

        EventHandler<ActionEvent> callSupplierSearchHandler;
        callSupplierSearchHandler = new CallSupplierSearchHandler();
        callSupplierSearch.setOnAction(callSupplierSearchHandler);

        EventHandler<ActionEvent> callSupplierCreationHandler;
        callSupplierCreationHandler = new CallSupplierCreationHandler();
        callSupplierCreation.setOnAction(callSupplierCreationHandler);

        SendRequestHandler sendRequestHandler = new SendRequestHandler(
                productsTable, suppliersTable, observationsField);
        sendRequest.setOnAction(sendRequestHandler);

        Callback<TableColumn.CellDataFeatures<ProductOnBudget, String>, ObservableValue<String>> productCellValueFactory;
        productCellValueFactory = new ProductCellValueFactory();
        productColumn.setCellValueFactory(productCellValueFactory);

        Callback<TableColumn<ProductOnBudget, String>, TableCell<ProductOnBudget, String>> quantityFieldCellFactory;
        quantityFieldCellFactory = new QuantityFieldCellFactory();

        Callback<TableColumn.CellDataFeatures<ProductOnBudget, String>, ObservableValue<String>> quantityCellValueFactory;
        quantityCellValueFactory = new QuantityCellValueFactory();
        quantityColumn.setCellValueFactory(quantityCellValueFactory);
        quantityColumn.setCellFactory(quantityFieldCellFactory);

        Callback<TableColumn<ProductOnBudget, String>, TableCell<ProductOnBudget, String>> unitOfMeasureFieldCellFactory;
        unitOfMeasureFieldCellFactory = new unitOfMeasureFieldCellFactory();

        Callback<TableColumn.CellDataFeatures<ProductOnBudget, String>, ObservableValue<String>> unitOfMeasureCellValueFactory;
        unitOfMeasureCellValueFactory = new PropertyValueFactory<>(
                "unitOfMeasure");
        unitOfMeasureColumn.setCellValueFactory(unitOfMeasureCellValueFactory);
        unitOfMeasureColumn.setCellFactory(unitOfMeasureFieldCellFactory);

        Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>> tradingNameCellValueFactory;
        tradingNameCellValueFactory = new PropertyValueFactory<>("tradingName");
        tradingNameColumn.setCellValueFactory(tradingNameCellValueFactory);

        Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>> companyNameCellValueFactory;
        companyNameCellValueFactory = new PropertyValueFactory<>("companyName");
        companyNameColumn.setCellValueFactory(companyNameCellValueFactory);

        Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>> einCellValueFactory;
        einCellValueFactory = new PropertyValueFactory<>(
                "employersIdentificationNumber");
        einColumn.setCellValueFactory(einCellValueFactory);

        Callback<TableColumn<ProductOnBudget, String>, TableCell<ProductOnBudget, String>> removeButtonCellFactory;
        removeButtonCellFactory = new RemoveButtonCellFactory();

        removeProductColumn.setCellFactory(removeButtonCellFactory);
        removeSupplierColumn.setCellFactory(removeButtonCellFactory);
    }

}
