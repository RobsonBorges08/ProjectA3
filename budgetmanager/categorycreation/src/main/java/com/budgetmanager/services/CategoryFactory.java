package com.budgetmanager.services;

import com.budgetmanager.core.exceptions.InvalidCategoryException;
import com.budgetmanager.domain.Category;

public class CategoryFactory {
    public Category makeCategory(String name) throws InvalidCategoryException {
        Category newCategory = new Category();
        
        if (name.isBlank()) {
            throw new InvalidCategoryException("A name must be set");
        }
        
        newCategory.setName(name);
        
        return newCategory;
    }
}
