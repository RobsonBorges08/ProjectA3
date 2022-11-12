package com.budgetmanager.application.handlers;

import com.budgetmanager.application.services.DialogService;
import com.budgetmanager.core.exceptions.InvalidSettingsException;
import com.budgetmanager.core.exceptions.NotEnoughDataException;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import com.budgetmanager.handlers.CreateBudgetRequestHandler;
import com.budgetrequest.domain.budgetrequest.BudgetRequestSetData;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;

public class SendRequestHandler implements EventHandler<ActionEvent> {

    private final TableView<ProductOnBudget> productsTable;
    private final TableView<Supplier> suppliersTable;
    private final TextField observationsField;

    public SendRequestHandler(TableView<ProductOnBudget> productsTable,
            TableView<Supplier> suppliersTable, TextField observationsField) {
        this.productsTable = productsTable;
        this.suppliersTable = suppliersTable;
        this.observationsField = observationsField;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            BudgetRequestSetData requestsSetData = new BudgetRequestSetData();

            Collection<ProductOnBudget> productList = productsTable.getItems();
            Collection<Supplier> supplierList = suppliersTable.getItems();

            boolean thereIsProduct = productList.isEmpty();
            boolean thereIsSupplier = supplierList.isEmpty();
            boolean thereIsEnoughData = thereIsProduct && thereIsSupplier;

            if (!thereIsEnoughData) {
                throw new NotEnoughDataException(
                        "Pelo menos um produto e um fornecedor deve ser informado");
            }

            String observations = observationsField.getText();

            Set<ProductOnBudget> products = new HashSet<>(productList);
            Set<Supplier> suppliers = new HashSet<>(supplierList);

            requestsSetData.setProducts(products);
            requestsSetData.setSuppliers(suppliers);
            requestsSetData.setObservations(observations);

            CreateBudgetRequestHandler handler = new CreateBudgetRequestHandler(
                    requestsSetData);
            handler.handle(t);

            DialogService.showSuccessMessage("Pedidos de orçamento enviados!");

        } catch (IOException | InvalidSettingsException | URISyntaxException |
                NotEnoughDataException | MessagingException exception) {
            DialogService.showErrorMessage(exception);
            Logger.getLogger(SendRequestHandler.class.getName())
                    .log(Level.SEVERE, null, exception);
        } catch (NumberFormatException exception) {
            DialogService.showErrorMessage("Pelo menos um produto e um fornecedor deve ser informado");
            Logger.getLogger(SendRequestHandler.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
    }

}
