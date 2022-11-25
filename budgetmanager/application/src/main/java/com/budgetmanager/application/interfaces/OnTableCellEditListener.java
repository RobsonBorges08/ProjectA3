/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.budgetmanager.application.interfaces;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Lucas
 * @param <S>
 */
public interface OnTableCellEditListener<S extends Object> extends ChangeListener<Boolean> {
    
    public void setCell(TableCell<S, String> itemIndex);
    
    public void setItemsTable(TableView<S> itemsTable);
    
    public void setPropertyField(TextField propertyField);
    
}
