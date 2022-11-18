package com.budgetmanager.handlers;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.core.exceptions.CategoryNotFoundException;
import com.budgetmanager.core.exceptions.InvalidProductException;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.ReadOnlyProduct;
import com.budgetmanager.domain.product.ProductData;
import com.budgetmanager.services.ProductFactory;
import com.budgetmanager.services.ReadOnlyProductFactory;
import javafx.event.ActionEvent;


public class CreateProductHandler {
    
    private final UnitOfWork unitOfWork;
    private final ProductData productData;

    public CreateProductHandler(ProductData productData) {
        this.unitOfWork = new UnitOfWork();
        this.productData = productData;
    }

    public Product handle(ActionEvent t) throws CategoryNotFoundException, InvalidProductException {
        ProductFactory productFactory = new ProductFactory(unitOfWork);
        ReadOnlyProductFactory readOnlyProductFactory = new ReadOnlyProductFactory();

        Product newProduct = productFactory.makeProduct(productData);
        unitOfWork.createProduct(newProduct);
        
        ReadOnlyProduct readOnlyProduct = readOnlyProductFactory.makeProduct(newProduct);
        unitOfWork.createReadOnlyProduct(readOnlyProduct);
        
        unitOfWork.commit();
        unitOfWork.close();
        
        return newProduct;
    }
    
}
