package com.budgetmanager.services;

import com.budgetmanager.domain.BudgetRequest;
import com.budgetmanager.domain.Settings;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

public class AsyncEmailSender implements Runnable {

    private final Settings settings;
    private final Collection<BudgetRequest> requests;

    public AsyncEmailSender(Settings settings, Collection<BudgetRequest> requests) throws IOException {
        this.settings = settings;
        this.requests = requests;
    }

    @Override
    public void run() {
        try ( EmailSender sender = new EmailSender(settings)) {
            sender.sendEmails(requests);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(AsyncEmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
