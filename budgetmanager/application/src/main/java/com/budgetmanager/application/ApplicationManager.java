package com.budgetmanager.application;

import java.util.logging.Logger;
import javafx.scene.control.TextField;

import static java.util.logging.Level.INFO;

public class ApplicationManager {

    private static TextField supplierCreationCategoriesField;

    public static TextField getSupplierCreationCategoriesField() {
        return supplierCreationCategoriesField;
    }

    public static void setSupplierCreationCategoriesField(
            TextField supplierCreationCategoriesField) {
        ApplicationManager.supplierCreationCategoriesField
                = supplierCreationCategoriesField;
    }
}
