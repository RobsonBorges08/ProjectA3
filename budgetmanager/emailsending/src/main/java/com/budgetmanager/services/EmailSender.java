package com.budgetmanager.services;

import com.budgetmanager.adapter.email.EmailSessionFactory;
import com.budgetmanager.domain.BudgetRequest;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Settings;
import com.budgetmanager.domain.Supplier;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender implements Closeable {

    private final String CONFIG_FILE_NAME = "emailserver.properties";
    private final String GREETINGS_HTML_FILE_NAME = "greetings.html";
    private final InputStream inputStream;
    private final Session session;
    private final Settings settings;

    public EmailSender(Settings settings) throws IOException {
        Class factoryClass = getClass();
        this.inputStream = factoryClass.getResourceAsStream(CONFIG_FILE_NAME);

        EmailSessionFactory factory = new EmailSessionFactory(inputStream);
        this.session = factory.makeSession();

        this.settings = settings;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
    
    public void sendEmails(Collection<BudgetRequest> newBudgetRequests) throws IOException, MessagingException {
        for (BudgetRequest request : newBudgetRequests) {
            sendEmail(request);
        }
    }

    public void sendEmail(BudgetRequest newBudgetRequest) throws IOException, AddressException, MessagingException {
        String greetings = generateGreetings();
        
        Collection<ProductOnBudget> products = newBudgetRequest.getProducts();
        String listOfItems = generateListOfItems(products);
        
        String signature = generateSignature();
        
        String fullMessage = greetings + listOfItems + signature;
        
        Supplier supplier = newBudgetRequest.getSupplier();
        String recipientEmail = supplier.getEmail();
        InternetAddress recipientInternetAddress;
        recipientInternetAddress = new InternetAddress(recipientEmail);
        sendMessageTo(fullMessage, recipientInternetAddress);
    }

    private String generateGreetings() throws IOException {
        Class senderClass = getClass();
        URL htmlFileURL = senderClass.getResource(GREETINGS_HTML_FILE_NAME);
        InputStream greetingsInputStream = htmlFileURL.openStream();
        InputStreamReader streamReader = new InputStreamReader(greetingsInputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }

        return stringBuilder.toString();
    }

    private String generateListOfItems(Collection<ProductOnBudget> products) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul>");
        String listItem;

        for (ProductOnBudget product : products) {
            listItem = "<li>" + product.toString() + "</li>";
            stringBuilder.append(listItem);
        }

        stringBuilder.append("</ul><br>");

        return stringBuilder.toString();
    }

    private String generateSignature() {
        String fullName = settings.getFullName();
        String occupation = settings.getOccupation();
        String companyName = settings.getCompanyName();
        String companyPhone = settings.getCompanyPhone();
        String companyStreet = settings.getCompanyStreet();
        int companyBuildingNumber = settings.getCompanyBuildingNumber();
        String companyCity = settings.getCompanyCity();
        String companyCountry = settings.getCompanyCountry();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<p>Atenciosamente,</p><br>");
        stringBuilder.append("<p>");
        stringBuilder.append(fullName);
        stringBuilder.append(" | ");
        stringBuilder.append(occupation);
        stringBuilder.append(" | ");
        stringBuilder.append(companyName);
        stringBuilder.append("</p><p>");
        stringBuilder.append(companyStreet);
        stringBuilder.append(", ");
        stringBuilder.append(companyBuildingNumber);
        stringBuilder.append(" | ");
        stringBuilder.append(companyCity);
        stringBuilder.append(", ");
        stringBuilder.append(companyCountry);
        stringBuilder.append(" | Telefone: ");
        stringBuilder.append(companyPhone);
        stringBuilder.append(" | </p>");
        
        return stringBuilder.toString();
    }

    private void sendMessageTo(String fullMessage, InternetAddress recipientAddress) throws MessagingException {
        String senderEmail = settings.getEmail();
        
        MimeMessage message = new MimeMessage(session);
        message.setFrom(senderEmail);
        message.addRecipient(Message.RecipientType.TO, recipientAddress);
        message.setSubject("Pedido de orçamento");
        message.setText(fullMessage);
        
        Transport.send(message);
    }

}
