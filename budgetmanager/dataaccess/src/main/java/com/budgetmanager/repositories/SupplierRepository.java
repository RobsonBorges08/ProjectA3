package com.budgetmanager.repositories;

import com.budgetmanager.domain.Supplier;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

public class SupplierRepository {

    Session session;

    public SupplierRepository(Session session) {
        this.session = session;
    }

    public void create(Supplier newSupplier) {
        session.persist(newSupplier);
    }

    public Supplier getById(int supplierId) {
        Class modelClass = Supplier.class;
        Supplier foundSupplier = 
                (Supplier) session.get(modelClass, supplierId);
        
        return foundSupplier;
    }

    public List<Supplier> list() {
        Class modelClass = Supplier.class;
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery criteriaQuery = builder.createQuery(modelClass);
        Query query = session.createQuery(criteriaQuery);
        
        return query.list();
    }

}
