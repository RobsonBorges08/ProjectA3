package com.budgetmanager.views;

import com.budgetmanager.adapters.DatabaseSessionFactory;
import com.budgetmanager.domain.Category;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class CategoryListView implements Closeable {

    private final Session session;

    public CategoryListView() {
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

    @Override
    public void close() throws IOException {
        session.close();
    }
    
    public static List<String> getCategoryNameList(List<Category> categoryList) {
        List<String> categoryNameList = new ArrayList<>();
        String categoryName;

        for (Category category : categoryList) {
            categoryName = category.getName();
            categoryNameList.add(categoryName);
        }

        return categoryNameList;
    }
}
