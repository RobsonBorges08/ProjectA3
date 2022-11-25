/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.services;

import com.budgetmanager.domain.ProductOnBudget;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Lucas
 */
public class QuantityCellValueFactory implements
        Callback<TableColumn.CellDataFeatures<ProductOnBudget, String>, ObservableValue<String>> {

    @Override
    public ObservableValue<String> call(
            TableColumn.CellDataFeatures<ProductOnBudget, String> productCell) {
        ProductOnBudget productOnBudget = productCell.getValue();
        double quantity = productOnBudget.getQuantity();
        String formattedQuantity = String.format("%.2f", quantity);

        return new SimpleStringProperty(formattedQuantity);
    }

}
