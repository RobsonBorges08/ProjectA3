package com.budgetmanager.handlers;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.core.exceptions.CategoryNotFoundException;
import com.budgetmanager.core.exceptions.InvalidProductException;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.ReadOnlyProduct;
import com.budgetmanager.domain.product.ProductData;
import com.budgetmanager.services.ProductFactory;
import com.budgetmanager.services.ReadOnlyProductFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class CreateProductHandler implements EventHandler<ActionEvent> {
    
    private final UnitOfWork unitOfWork;
    private final ProductData productData;

    public CreateProductHandler(ProductData productData) {
        this.unitOfWork = new UnitOfWork();
        this.productData = productData;
    }
    
    @Override
    public void handle(ActionEvent t) {
        try {
            ProductFactory productFactory = new ProductFactory(unitOfWork);
            ReadOnlyProductFactory readOnlyProductFactory = new ReadOnlyProductFactory();
            
            Product newProduct = productFactory.makeProduct(productData);
            ReadOnlyProduct readOnlyProduct = readOnlyProductFactory.makeProduct(newProduct);
            
            unitOfWork.createProduct(newProduct);
            unitOfWork.createReadOnlyProduct(readOnlyProduct);
        } catch (CategoryNotFoundException | InvalidProductException ex) {
            Logger.getLogger(CreateProductHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
