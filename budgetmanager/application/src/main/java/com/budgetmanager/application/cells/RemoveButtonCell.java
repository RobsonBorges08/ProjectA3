/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.cells;

import com.budgetmanager.application.handlers.RemoveButtonHandler;
import com.budgetmanager.domain.ProductOnBudget;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

/**
 *
 * @author Lucas
 */
public class RemoveButtonCell extends TableCell<ProductOnBudget, String> {
    
    private final Button removeButton = new Button("X");

    @Override
    protected void updateItem(String itemValue, boolean isEmpty) {
        super.updateItem(itemValue, isEmpty);
        
        TableView<ProductOnBudget> itemsTable = getTableView();
        int itemIndex = getIndex();
        
        EventHandler<ActionEvent> removeButtonHandler;
        removeButtonHandler = new RemoveButtonHandler(itemsTable, itemIndex);
        removeButton.setOnAction(removeButtonHandler);

        if (isEmpty) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(removeButton);
            setText(null);
        }
    }

}
