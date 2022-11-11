package com.budgetrequest.services;

import com.budgetmanager.domain.BudgetRequest;
import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import com.budgetrequest.domain.budgetrequest.BudgetRequestSetData;
import java.util.HashSet;
import java.util.Set;

public class BudgetRequestSetFactory {

    public Set<BudgetRequest> makeSet(BudgetRequestSetData budgetRequestsData) {
        Set<BudgetRequest> newBudgetRequestSet = new HashSet<>();
        Set<Supplier> suppliers = budgetRequestsData.getSuppliers();
        String observations = budgetRequestsData.getObservations();
        BudgetRequest request;
        Set<ProductOnBudget> products;

        for (Supplier supplier : suppliers) {
            request = new BudgetRequest();
            products = budgetRequestsData.getProducts();

            request.setProducts(products);
            request.setSupplier(supplier);
            request.setObservations(observations);

            newBudgetRequestSet.add(request);
        }

        return newBudgetRequestSet;
    }
}
