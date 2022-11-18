package com.budgetmanager.services;

import com.budgetmanager.domain.Category;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.ReadOnlyProduct;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class ReadOnlyProductFactory {

    private final Logger logger;

    public ReadOnlyProductFactory() {
        Class currentClass = getClass();
        this.logger = Logger.getLogger(currentClass.getName());
    }

    public ReadOnlyProduct makeProduct(Product newProduct) {
        ReadOnlyProduct readOnlyProduct = new ReadOnlyProduct();

        int id = newProduct.getId();
        readOnlyProduct.setId(id);
        logFieldWasSet("id", id);
        
        String description = newProduct.getDescription();
        readOnlyProduct.setDescription(description);
        logFieldWasSet("description", description);

        Category category = newProduct.getCategory();
        String categoryName = category.getName();
        readOnlyProduct.setCategory(categoryName);
        logFieldWasSet("category", categoryName);

        return readOnlyProduct;
    }

    private void logFieldWasSet(String fieldName, int value) {
        String valueAsString = String.valueOf(value);
        logFieldWasSet(fieldName, valueAsString);
    }
    
    private void logFieldWasSet(String fieldName, String value) {
        String logMessage = String.format("%s was set to the field %s", value,
                fieldName);
        logger.log(INFO, logMessage);
    }

}
