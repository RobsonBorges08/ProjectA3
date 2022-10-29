package com.budgetmanager.views;

import com.budgetmanager.adapters.DatabaseSessionFactory;
import com.budgetmanager.domain.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class ProductListView {

    private final Session session;

    public ProductListView(Session session) {
        this.session = DatabaseSessionFactory.makeSession();
    }

    public List<Product> list() {
        String readingQueryString = ("SELECT id, description, category "
                + "FROM read_only_products ORDER BY description");
        
        NativeQuery<Product> query;
        query = session.createNativeQuery(readingQueryString, Product.class);
        
        return query.list();
    }
    
    public List<Product> filterByDescription(String keywords) {
        String readingQueryString = ("SELECT id, description, category "
                + "FROM read_only_products "
                + "WHERE description LIKE '%" + keywords + "%'"
                + "ORDER BY description");
        
        NativeQuery<Product> query;
        query = session.createNativeQuery(readingQueryString, Product.class);
        
        return query.list();
    }

}
