package com.budgetmanager.adapter.email;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javax.mail.Session;

public class EmailSessionFactory {
    
    private InputStream inputStream;
    private final String CONFIG_FILE_NAME = "emailserver.properties";
    
    public Session makeSession() throws IOException {
        Properties properties = getProperties();
        
        return Session.getInstance(properties);
    }

    private Properties getProperties() throws IOException {
        Class factoryClass = getClass();
        URL configurationFileURL = factoryClass.getResource(CONFIG_FILE_NAME);
        inputStream = configurationFileURL.openStream();
        Properties properties = new Properties();
        properties.load(inputStream);
        
        return properties;
    }
}
