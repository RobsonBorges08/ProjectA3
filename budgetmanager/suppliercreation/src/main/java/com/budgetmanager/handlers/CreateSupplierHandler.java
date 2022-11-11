package com.budgetmanager.handlers;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.core.exceptions.InvalidSupplierException;
import com.budgetmanager.domain.Supplier;
import com.budgetmanager.domain.supplier.SupplierData;
import com.budgetmanager.services.SupplierFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CreateSupplierHandler implements EventHandler<ActionEvent> {

    private SupplierData supplierData;
    private UnitOfWork unitOfWork;

    public CreateSupplierHandler(SupplierData supplierData) {
        this.supplierData = supplierData;
        this.unitOfWork = new UnitOfWork();
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            SupplierFactory factory = new SupplierFactory();
            Supplier newSupplier = factory.makeSupplier(supplierData);
            unitOfWork.createSupplier(newSupplier);
        } catch (InvalidSupplierException ex) {
            Logger.getLogger(CreateSupplierHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
