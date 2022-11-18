package com.budgetmanager.services;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.domain.Category;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.product.ProductData;
import com.budgetmanager.core.exceptions.CategoryNotFoundException;
import com.budgetmanager.core.exceptions.InvalidProductException;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class ProductFactory {

    private UnitOfWork unitOfWork;
    private final Logger logger;

    public ProductFactory(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;

        Class currentClass = ProductFactory.class;
        this.logger = Logger.getLogger(currentClass.getName());
    }

    public Product makeProduct(ProductData productData)
            throws CategoryNotFoundException, InvalidProductException {
        Product newProduct = new Product();

        String description = productData.getDescription();
        checkDescription(description);
        newProduct.setDescription(description);
        logFieldWasSet("description", description);

        String categoryName = productData.getCategoryName();
        Category category = unitOfWork.getCategoryByName(categoryName);
        newProduct.setCategory(category);

        int categoryId = category.getId();
        String categoryAsString = String.format("%s(%d)", categoryName,
                categoryId);
        logFieldWasSet("category", categoryAsString);

        return newProduct;
    }

    private void logFieldWasSet(String fieldName, String value) {
        String logMessage = String.format("%s was set to the field %s", value,
                fieldName);
        logger.log(INFO, logMessage);
    }

    private void checkDescription(String description) throws
            InvalidProductException {
        if (description.isBlank()) {
            throw new InvalidProductException(
                    "A description must be set"
            );
        }
    }

}
