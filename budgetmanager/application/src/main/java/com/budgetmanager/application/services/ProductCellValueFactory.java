package com.budgetmanager.application.services;

import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.ProductOnBudget;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ProductCellValueFactory implements
        Callback<TableColumn.CellDataFeatures<ProductOnBudget, String>, ObservableValue<String>> {

    @Override
    public ObservableValue<String> call(
            TableColumn.CellDataFeatures<ProductOnBudget, String> observableProduct) {
        ProductOnBudget productOnBudget = observableProduct.getValue();
        Product product = productOnBudget.getProduct();
        String description = product.getDescription();

        return new SimpleStringProperty(description);
    }
}
