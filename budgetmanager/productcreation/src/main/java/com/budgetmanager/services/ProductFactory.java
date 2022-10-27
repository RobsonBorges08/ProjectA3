package com.budgetmanager.services;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.domain.Category;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.product.ProductData;
import com.budgetmanager.core.exceptions.CategoryNotFoundException;
import com.budgetmanager.core.exceptions.InvalidProductException;

public class ProductFactory {

    private UnitOfWork unitOfWork;

    public ProductFactory(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Product makeProduct(ProductData productData)
            throws CategoryNotFoundException, InvalidProductException {
        Product newProduct = new Product();

        String description = productData.getDescription();
        checkDescription(description);
        newProduct.setDescription(description);

        String categoryName = productData.getCategoryName();
        Category category = unitOfWork.getCategoryByName(categoryName);
        newProduct.setCategory(category);

        return newProduct;
    }

    private void checkDescription(String description) throws InvalidProductException {
        if (description.isBlank()) {
            throw new InvalidProductException(
                    "A description must be set"
            );
        }
    }

}
