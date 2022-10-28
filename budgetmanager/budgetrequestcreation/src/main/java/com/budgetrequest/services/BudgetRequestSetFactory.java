package com.budgetrequest.services;

import com.budgetmanager.domain.BudgetRequest;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import com.budgetrequest.domain.budgetrequest.BudgetRequestSetData;
import java.util.HashSet;
import java.util.Set;

public class BudgetRequestSetFactory {

    public Set<BudgetRequest> makeSet(BudgetRequestSetData data) {
        Set<BudgetRequest> newBudgetRequestSet = new HashSet<>();
        Set<Supplier> suppliers = data.getSuppliers();
        String observations = data.getObservations();
        BudgetRequest request;
        Set<ProductOnBudget> products;

        for (Supplier supplier : suppliers) {
            request = new BudgetRequest();
            products = data.getProducts();

            request.setProducts(products);
            request.setSupplier(supplier);
            request.setObservations(observations);

            newBudgetRequestSet.add(request);
        }

        return newBudgetRequestSet;
    }
}
