package com.budgetmanager.adapter.email;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Session;

public class EmailSessionFactory {
    
    private final Properties properties;

    public EmailSessionFactory(InputStream inputStream) throws IOException {
        properties = new Properties();
        properties.load(inputStream);
    }

    public Session makeSession() {
        Authenticator authenticator = new SmtpAuthenticator(properties);

        return Session.getDefaultInstance(properties, authenticator);
    }
}
