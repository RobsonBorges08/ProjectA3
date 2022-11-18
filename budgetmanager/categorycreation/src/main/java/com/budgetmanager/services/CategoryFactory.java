package com.budgetmanager.services;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.core.exceptions.InvalidCategoryException;
import com.budgetmanager.domain.Category;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class CategoryFactory {

    private final Logger logger;

    public CategoryFactory() {
        Class currentClass = CategoryFactory.class;
        this.logger = Logger.getLogger(currentClass.getName());
    }

    public Category makeCategory(String name) throws InvalidCategoryException {
        Category newCategory = new Category();

        if (name.isBlank()) {
            throw new InvalidCategoryException("A name must be set");
        }

        newCategory.setName(name);
        logFieldWasSet("name", name);

        return newCategory;
    }

    private void logFieldWasSet(String fieldName, String value) {
        String logMessage = String.format("%s was set to the field %s", value,
                fieldName);
        logger.log(INFO, logMessage);
    }
}
