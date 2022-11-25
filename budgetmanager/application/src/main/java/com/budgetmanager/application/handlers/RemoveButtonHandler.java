/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.handlers;

import com.budgetmanager.domain.ProductOnBudget;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;


public class RemoveButtonHandler implements EventHandler<ActionEvent> {
    
    private TableView<ProductOnBudget> itemsTable;
    private int itemIndex;

    public RemoveButtonHandler(TableView<ProductOnBudget> itemsTable,
            int itemIndex) {
        this.itemsTable = itemsTable;
        this.itemIndex = itemIndex;
    }

    @Override
    public void handle(ActionEvent t) {
        itemsTable.getItems().remove(itemIndex);
    }
    
}
