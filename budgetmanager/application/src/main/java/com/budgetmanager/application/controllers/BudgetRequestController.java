package com.budgetmanager.application.controllers;

import com.budgetmanager.application.handlers.CallProductCreationHandler;
import com.budgetmanager.application.handlers.SwitchToSettingsHandler;
import com.budgetmanager.application.handlers.SendRequestHandler;
import com.budgetmanager.application.interfaces.SceneFactory;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BudgetRequestController extends SceneFactory {

    private double width = 640;
    private double height = 480;

    @FXML
    private Button switchToSettings;

    @FXML
    private Button callProductCreation;

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
        
        SendRequestHandler sendRequestHandler = new SendRequestHandler(productsTable, suppliersTable, observationsField);
        sendRequest.setOnAction(sendRequestHandler);
    }

    @Override
    public Class getControllerClass() {
        return getClass();
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

}
