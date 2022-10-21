package com.budgetmanager.dataaccess.adapters;

import java.net.URL;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class DatabaseSessionFactory {
    
    private static final Class FACTORY_CLASS = DatabaseSessionFactory.class;
    
    public static Session makeSession() {
        URL configurationFile = 
                FACTORY_CLASS.getResource("hibernate.cfg.xml");
        
        Configuration configuration = new Configuration();
        configuration.configure(configurationFile);
        org.hibernate.SessionFactory factory = 
                configuration.buildSessionFactory();
        
        return factory.openSession();
    }
}
