package com.budgetmanager.repositories;

import com.budgetmanager.domain.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

public class ProductRepository {

    Session session;

    public ProductRepository(Session session) {
        this.session = session;
    }

    public void create(Product newProduct) {
        session.persist(newProduct);
    }

    public Product getById(int productId) {
        Class modelClass = Product.class;
        Product foundProduct = 
                (Product) session.get(modelClass, productId);
        
        return foundProduct;
    }

    public List<Product> list() {
        Class modelClass = Product.class;
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery criteriaQuery = builder.createQuery(modelClass);
        Query query = session.createQuery(criteriaQuery);
        
        return query.list();
    }

}
