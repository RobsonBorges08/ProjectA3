package com.budgetmanager.application.controllers;

import com.budgetmanager.application.handlers.CallProductCreationHandler;
import com.budgetmanager.application.handlers.CallSupplierCreationHandler;
import com.budgetmanager.application.handlers.SwitchToSettingsHandler;
import com.budgetmanager.application.handlers.SendRequestHandler;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BudgetRequestController implements Initializable {

    @FXML
    private Button switchToSettings;

    @FXML
    private Button callProductCreation;

    @FXML
    private Button callSupplierCreation;

    @FXML
    private Button sendRequest;

    @FXML
    private ImageView gearImageView;

    @FXML
    private TableView<ProductOnBudget> productsTable;

    @FXML
    private TableView<Supplier> suppliersTable;

    @FXML
    private TextField observationsField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Class currentClass = getClass();

        URL gearURL = currentClass.getResource("gear.png");
        String gearPathString = gearURL.toString();
        Image gearImage = new Image(gearPathString);
        gearImageView.setImage(gearImage);

        EventHandler<ActionEvent> switchToSettingsHandler;
        switchToSettingsHandler = new SwitchToSettingsHandler(currentClass);
        switchToSettings.setOnAction(switchToSettingsHandler);

        EventHandler<ActionEvent> callProductCreationHandler;
        callProductCreationHandler = new CallProductCreationHandler();
        callProductCreation.setOnAction(callProductCreationHandler);

        CallSupplierCreationHandler callSupplierCreationHandler;
        callSupplierCreationHandler = new CallSupplierCreationHandler();
        callSupplierCreation.setOnAction(callSupplierCreationHandler);

        SendRequestHandler sendRequestHandler = new SendRequestHandler(
                productsTable, suppliersTable, observationsField);
        sendRequest.setOnAction(sendRequestHandler);
    }

}
