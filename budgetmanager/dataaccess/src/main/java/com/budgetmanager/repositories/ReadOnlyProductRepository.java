package com.budgetmanager.repositories;

import com.budgetmanager.domain.ReadOnlyProduct;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

public class ReadOnlyProductRepository {

    Session session;

    public ReadOnlyProductRepository(Session session) {
        this.session = session;
    }

    public void create(ReadOnlyProduct newProduct) {
        session.persist(newProduct);
    }

    public ReadOnlyProduct getById(int productId) {
        Class modelClass = ReadOnlyProduct.class;
        ReadOnlyProduct foundProduct = 
                (ReadOnlyProduct) session.get(modelClass, productId);
        
        return foundProduct;
    }

    public List<ReadOnlyProduct> list() {
        Class modelClass = ReadOnlyProduct.class;
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery criteriaQuery = builder.createQuery(modelClass);
        Query query = session.createQuery(criteriaQuery);
        
        return query.list();
    }

}
