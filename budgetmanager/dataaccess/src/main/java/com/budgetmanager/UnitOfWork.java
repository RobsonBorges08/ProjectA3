package com.budgetmanager;

import com.budgetmanager.repositories.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.budgetmanager.adapters.DatabaseSessionFactory;
import com.budgetmanager.core.exceptions.CategoryNotFoundException;
import com.budgetmanager.core.exceptions.ProductNotFoundException;
import com.budgetmanager.core.exceptions.SupplierNotFoundException;
import com.budgetmanager.domain.Category;
import com.budgetmanager.domain.Product;
import com.budgetmanager.domain.Supplier;
import java.util.List;

public class UnitOfWork {

    private final ProductRepository products;
    private final SupplierRepository suppliers;
    private final CategoryRepository categories;
    private final Session session;
    private Transaction transaction;

    public UnitOfWork() {
        this.session = DatabaseSessionFactory.makeSession();
        this.products = new ProductRepository(session);
        this.suppliers = new SupplierRepository(session);
        this.categories = new CategoryRepository(session);
    }

    public void createProduct(Product newProduct) {
        transaction = session.beginTransaction();
        products.create(newProduct);
    }

    public void createSupplier(Supplier newSupplier) {
        transaction = session.beginTransaction();
        suppliers.create(newSupplier);
    }

    public void createCategory(Category newCategory) {
        transaction = session.beginTransaction();
        categories.create(newCategory);
    }

    public Product getProductById(int productId) throws ProductNotFoundException {
        Product foundProduct = products.getById(productId);

        if (foundProduct != null) {
            return foundProduct;
        }

        throw new ProductNotFoundException(
                "There is no category with id " + productId
        );
    }

    public Supplier getSupplierById(int supplierId) throws SupplierNotFoundException {
        Supplier foundSupplier = suppliers.getById(supplierId);

        if (foundSupplier != null) {
            return foundSupplier;
        }

        throw new SupplierNotFoundException(
                "There is no category with id " + supplierId
        );
    }

    public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
        Category foundCategory = categories.getById(categoryId);

        if (foundCategory != null) {
            return foundCategory;
        }

        throw new CategoryNotFoundException(
                "There is no category with id " + categoryId
        );
    }

    public Category getCategoryByName(String categoryName) throws CategoryNotFoundException {
        Category foundCategory = categories.getByName(categoryName);

        if (foundCategory != null) {
            return foundCategory;
        }

        throw new CategoryNotFoundException(
                "There is no category with name " + categoryName
        );
    }

    public List<Product> listProducts() {
        return products.list();
    }

    public List<Supplier> listSuppliers() {
        return suppliers.list();
    }

    public List<Category> listCategories() {
        return categories.list();
    }

    public void commit() {
        transaction.commit();
    }

    public void close() {
        if (transaction.isActive()) {
            transaction.rollback();
        }

        session.close();
    }
}
