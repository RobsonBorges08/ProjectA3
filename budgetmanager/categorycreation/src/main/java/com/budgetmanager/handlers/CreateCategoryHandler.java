/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.handlers;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.core.exceptions.InvalidCategoryException;
import com.budgetmanager.domain.Category;
import com.budgetmanager.services.CategoryFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Lucas
 */
public class CreateCategoryHandler implements EventHandler<ActionEvent> {

    private final String categoryName;
    private final UnitOfWork unitOfWork;

    public CreateCategoryHandler(String categoryName) {
        this.unitOfWork = new UnitOfWork();
        this.categoryName = categoryName;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            CategoryFactory factory = new CategoryFactory();

            Category newCategory = factory.makeCategory(categoryName);
            unitOfWork.createCategory(newCategory);

        } catch (InvalidCategoryException ex) {
            Logger.getLogger(CreateCategoryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
