/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.services;

import com.budgetmanager.application.cells.RemoveButtonCell;
import com.budgetmanager.domain.ProductOnBudget;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Lucas
 */
public class RemoveButtonCellFactory implements
        Callback<TableColumn<ProductOnBudget, String>, TableCell<ProductOnBudget, String>> {

    @Override
    public TableCell<ProductOnBudget, String> call(
            TableColumn<ProductOnBudget, String> p) {
        return new RemoveButtonCell();
    }

}
