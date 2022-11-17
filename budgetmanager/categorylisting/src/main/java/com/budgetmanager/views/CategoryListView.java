package com.budgetmanager.views;

import com.budgetmanager.adapters.DatabaseSessionFactory;
import com.budgetmanager.domain.Category;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class CategoryListView {

    private final Session session;

    public CategoryListView(Session session) {
        this.session = DatabaseSessionFactory.makeSession();
    }
    
    public List<Category> list() {
        String readingQueryString = ("SELECT id, name "
                + "FROM categories ORDER BY name");
        
        NativeQuery<Category> query;
        query = session.createNativeQuery(readingQueryString, Category.class);
        
        return query.list();
    }
    
    public List<Category> filterByName(String keywords) {
        String readingQueryString = ("SELECT id, name "
                + "FROM categories "
                + "WHERE name LIKE '%" + keywords + "%'"
                + "ORDER BY name");
        
        NativeQuery<Category> query;
        query = session.createNativeQuery(readingQueryString, Category.class);
        
        return query.list();
    }
    
}
