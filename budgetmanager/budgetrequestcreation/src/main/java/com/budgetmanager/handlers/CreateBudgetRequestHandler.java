/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.handlers;

import com.budgetmanager.core.exceptions.InvalidSettingsException;
import com.budgetmanager.domain.BudgetRequest;
import com.budgetmanager.domain.Settings;
import com.budgetmanager.services.EmailSender;
import com.budgetrequest.domain.budgetrequest.BudgetRequestSetData;
import com.budgetrequest.services.BudgetRequestSetFactory;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javax.mail.MessagingException;
import com.budgetmanager.services.SettingsService;
import java.net.URISyntaxException;

public class CreateBudgetRequestHandler implements EventHandler<ActionEvent> {

    private BudgetRequestSetData budgetRequestsData;
    private EmailSender sender;

    public CreateBudgetRequestHandler(BudgetRequestSetData budgetRequestsData) throws IOException, InvalidSettingsException, URISyntaxException {
        this.budgetRequestsData = budgetRequestsData;
        
        SettingsService service = new SettingsService();
        Settings settings = service.getSettings();
        this.sender = new EmailSender(settings);
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            BudgetRequestSetFactory factory = new BudgetRequestSetFactory();
            Set<BudgetRequest> requests = factory.makeSet(budgetRequestsData);
            sender.sendEmails(requests);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(CreateBudgetRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
