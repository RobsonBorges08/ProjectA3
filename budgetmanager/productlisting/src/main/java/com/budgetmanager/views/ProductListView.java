package com.budgetmanager.views;

import com.budgetmanager.adapters.DatabaseSessionFactory;
import com.budgetmanager.domain.ReadOnlyProduct;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class ProductListView {

    private final Session session;

    public ProductListView() {
        this.session = DatabaseSessionFactory.makeSession();
    }

    public List<ReadOnlyProduct> list() {
        String readingQueryString = ("SELECT id, description, category "
                + "FROM read_only_products ORDER BY description");

        NativeQuery<ReadOnlyProduct> query;
        query = session.createNativeQuery(readingQueryString,
                ReadOnlyProduct.class);

        return query.list();
    }

    public List<ReadOnlyProduct> filterByDescription(String keywords) {
        String readingQueryString = ("SELECT id, description, category "
                + "FROM read_only_products "
                + "WHERE description LIKE '%" + keywords + "%'"
                + "ORDER BY description");

        NativeQuery<ReadOnlyProduct> query;
        query = session.createNativeQuery(readingQueryString,
                ReadOnlyProduct.class);

        return query.list();
    }

}
