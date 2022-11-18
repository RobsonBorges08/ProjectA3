/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.handlers;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.core.exceptions.InvalidCategoryException;
import com.budgetmanager.domain.Category;
import com.budgetmanager.services.CategoryFactory;
import javafx.event.ActionEvent;

/**
 *
 * @author Lucas
 */
public class CreateCategoryHandler {

    private final String categoryName;
    private final UnitOfWork unitOfWork;

    public CreateCategoryHandler(String categoryName) {
        this.unitOfWork = new UnitOfWork();
        this.categoryName = categoryName;
    }

    public Category handle(ActionEvent t) throws InvalidCategoryException {
        CategoryFactory factory = new CategoryFactory();

        Category newCategory = factory.makeCategory(categoryName);
        unitOfWork.createCategory(newCategory);
        unitOfWork.commit();
        unitOfWork.close();
        
        return newCategory;
    }

}
