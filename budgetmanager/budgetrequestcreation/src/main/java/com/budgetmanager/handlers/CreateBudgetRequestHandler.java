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
import javafx.event.ActionEvent;
import javax.mail.MessagingException;
import com.budgetmanager.services.SettingsService;
import java.net.URISyntaxException;

public class CreateBudgetRequestHandler {

    private BudgetRequestSetData budgetRequestsData;
    private EmailSender sender;

    public CreateBudgetRequestHandler(BudgetRequestSetData budgetRequestsData)
            throws IOException, InvalidSettingsException, URISyntaxException {
        this.budgetRequestsData = budgetRequestsData;

        SettingsService service = new SettingsService();
        Settings settings = service.getSettings();
        this.sender = new EmailSender(settings);
    }

    public void handle(ActionEvent t) throws MessagingException, IOException {
        BudgetRequestSetFactory factory = new BudgetRequestSetFactory();
        Set<BudgetRequest> requests = factory.makeSet(budgetRequestsData);
        sender.sendEmails(requests);
    }

}
