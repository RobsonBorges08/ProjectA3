package com.budgetmanager.views;

import com.budgetmanager.adapters.DatabaseSessionFactory;
import com.budgetmanager.domain.Supplier;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class SupplierListView {
    
    private final Session session;

    public SupplierListView() {
        this.session = DatabaseSessionFactory.makeSession();
    }

    public List<Supplier> list() {
        String readingQueryString = (
                "SELECT tradingName, companyName, city, country "
                + "FROM suppliers "
                + "ORDER BY tradingName"
        );
        
        Query<Supplier> query;
        query = session.createNativeQuery(readingQueryString, Supplier.class);
        
        return query.list();
    }
    
    public List<Supplier> filterByTradingName(String keywords) {
        String readingQueryString = (
                "SELECT tradingName, companyName, city, country "
                + "FROM suppliers "
                + "WHERE tradingName LIKE '%" + keywords + "%' "
                + "ORDER BY tradingName"
        );
        
        Query<Supplier> query;
        query = session.createNativeQuery(readingQueryString, Supplier.class);
        
        return query.list();
    }
}
