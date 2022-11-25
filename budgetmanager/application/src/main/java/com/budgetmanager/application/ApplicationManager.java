package com.budgetmanager.application;

import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ApplicationManager {

    private static TextField supplierCreationCategoriesField;
    private static TableView<ProductOnBudget> productsTable;
    private static TableView<Supplier> suppliersTable;

    public static TableView<Supplier> getSuppliersTable() {
        return suppliersTable;
    }

    public static void setSuppliersTable(TableView<Supplier> suppliersTable) {
        ApplicationManager.suppliersTable = suppliersTable;
    }

    public static TableView<ProductOnBudget> getProductsTable() {
        return productsTable;
    }

    public static void setProductsTable(TableView<ProductOnBudget> productsTable) {
        ApplicationManager.productsTable = productsTable;
    }

    public static TextField getSupplierCreationCategoriesField() {
        return supplierCreationCategoriesField;
    }

    public static void setSupplierCreationCategoriesField(
            TextField supplierCreationCategoriesField) {
        ApplicationManager.supplierCreationCategoriesField
                = supplierCreationCategoriesField;
    }
}
