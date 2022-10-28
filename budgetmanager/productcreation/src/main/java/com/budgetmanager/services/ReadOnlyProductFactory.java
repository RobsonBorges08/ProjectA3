package com.budgetmanager.services;

import com.budgetmanager.domain.Category;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.ReadOnlyProduct;

public class ReadOnlyProductFactory {
    
    public ReadOnlyProduct makeProduct(Product newProduct) {
        ReadOnlyProduct readOnlyProduct = new ReadOnlyProduct();
        
        int id = newProduct.getId();
        String description = newProduct.getDescription();
        Category category = newProduct.getCategory();
        String categoryName = category.getName();
        
        readOnlyProduct.setId(id);
        readOnlyProduct.setDescription(description);
        readOnlyProduct.setCategory(categoryName);
        
        return readOnlyProduct;
    }
    
}
