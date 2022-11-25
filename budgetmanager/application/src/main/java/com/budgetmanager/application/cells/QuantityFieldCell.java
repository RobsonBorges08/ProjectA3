/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.cells;

import com.budgetmanager.domain.ProductOnBudget;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class QuantityFieldCell extends TableCell<ProductOnBudget, String> {
    
    @Override
    protected void updateItem(String itemValue, boolean isEmpty) {
        super.updateItem(itemValue, isEmpty);
        
        TextField cellField = new TextField();
        cellField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> ov, Boolean t,
                    Boolean newValue) {
                if (!newValue) {
                    String enteredValue = cellField.getText();
                    updateItem(enteredValue, false);
                }
            }
        });
        itemValue = formatEnteredValue(itemValue);
        
        if (isEmpty) {
            setGraphic(null);
            setText(null);
            
        } else {
            cellField.setText(itemValue);
            setGraphic(cellField);
            setText(null);
            
        }
    }
    
    private String formatEnteredValue(String itemValue) {
        try {
            double valueAsDouble = Double.parseDouble(itemValue);
            getTableView().getItems().get(getIndex()).setQuantity(valueAsDouble);
            
            return String.format("%.2f", valueAsDouble);
            
        } catch (NullPointerException | NumberFormatException exception) {
            return "0.00";
        }
    }
}
