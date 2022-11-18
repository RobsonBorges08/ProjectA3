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
import com.budgetmanager.domain.ReadOnlyProduct;
import com.budgetmanager.domain.Supplier;
import java.io.Closeable;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class UnitOfWork implements Closeable {

    private final ProductRepository products;
    private final ReadOnlyProductRepository readOnlyProducts;
    private final SupplierRepository suppliers;
    private final CategoryRepository categories;
    private final Session session;
    private Transaction transaction;
    private final Logger logger;

    public UnitOfWork() {
        this.session = DatabaseSessionFactory.makeSession();

        this.products = new ProductRepository(session);
        this.readOnlyProducts = new ReadOnlyProductRepository(session);
        this.suppliers = new SupplierRepository(session);
        this.categories = new CategoryRepository(session);

        Class currentClass = UnitOfWork.class;
        this.logger = Logger.getLogger(currentClass.getName());
    }

    public void createProduct(Product newProduct) {
        beginTransaction();
        products.create(newProduct);
        logNewRegistry("Product", newProduct.getId());
    }

    public void createReadOnlyProduct(ReadOnlyProduct newProduct) {
        beginTransaction();
        readOnlyProducts.create(newProduct);
        logNewRegistry("Read only product", newProduct.getId());
    }

    public void createSupplier(Supplier newSupplier) {
        beginTransaction();
        suppliers.create(newSupplier);
        logNewRegistry("Supplier", newSupplier.getId());
    }

    public void createCategory(Category newCategory) {
        beginTransaction();
        categories.create(newCategory);
        logNewRegistry("Category", newCategory.getId());
    }
    
    private void beginTransaction() {
        if (transaction == null) {
            transaction = session.beginTransaction();
        }
    }

    private void logNewRegistry(String modelName, int id) {
        String logMessage = String.format("%s %d regitered", modelName, id);
        logger.log(INFO, logMessage);
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

    public Supplier getSupplierById(int supplierId) throws
            SupplierNotFoundException {
        Supplier foundSupplier = suppliers.getById(supplierId);

        if (foundSupplier != null) {
            return foundSupplier;
        }

        throw new SupplierNotFoundException(
                "There is no category with id " + supplierId
        );
    }

    public Category getCategoryById(int categoryId) throws
            CategoryNotFoundException {
        Category foundCategory = categories.getById(categoryId);

        if (foundCategory != null) {
            return foundCategory;
        }

        throw new CategoryNotFoundException(
                "There is no category with id " + categoryId
        );
    }

    public Category getCategoryByName(String categoryName) throws
            CategoryNotFoundException {
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
        logger.log(INFO, "Data commited");
    }

    @Override
    public void close() {
        if (transaction.isActive()) {
            transaction.rollback();
        }

        session.close();
    }
}
