package com.budgetmanager.repositories;

import com.budgetmanager.domain.Category;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

public class CategoryRepository {

    Session session;

    public CategoryRepository(Session session) {
        this.session = session;
    }

    public void create(Category newCategory) {
        session.persist(newCategory);
    }

    public Category getById(int categoryId) {
        Class modelClass = getClass();
        Category foundCategory = 
                (Category) session.get(modelClass, categoryId);
        
        return foundCategory;
    }

    public List<Category> list() {
        Class modelClass = getClass();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery criteriaQuery = builder.createQuery(modelClass);
        Query query = session.createQuery(criteriaQuery);
        
        return query.list();
    }

}
