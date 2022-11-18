package com.budgetmanager.handlers;

import com.budgetmanager.UnitOfWork;
import com.budgetmanager.core.exceptions.InvalidSupplierException;
import com.budgetmanager.domain.Supplier;
import com.budgetmanager.domain.supplier.SupplierData;
import com.budgetmanager.services.SupplierFactory;
import javafx.event.ActionEvent;

public class CreateSupplierHandler {

    private final SupplierData supplierData;
    private final UnitOfWork unitOfWork;

    public CreateSupplierHandler(SupplierData supplierData) {
        this.supplierData = supplierData;
        this.unitOfWork = new UnitOfWork();
    }

    public Supplier handle(ActionEvent t) throws InvalidSupplierException {
        try (unitOfWork) {
            SupplierFactory factory = new SupplierFactory();
            
            Supplier newSupplier = factory.makeSupplier(supplierData);
            unitOfWork.createSupplier(newSupplier);
            unitOfWork.commit();
            
            return newSupplier;
        }
    }

}
