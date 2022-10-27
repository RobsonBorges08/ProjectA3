package com.budgetmanager.adapter.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {

    private final Properties properties;

    public SmtpAuthenticator(Properties properties) {
        this.properties = properties;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String userName = properties.getProperty("smtp.username");
        String password = properties.getProperty("smtp.password");
        return new PasswordAuthentication(userName, password);
    }

}
