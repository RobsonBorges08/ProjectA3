package com.budgetrequest.domain.budgetrequest;

import com.budgetmanager.domain.ProductOnBudget;
import com.budgetmanager.domain.Supplier;
import java.util.Set;
import lombok.Data;


@Data
public class BudgetRequestSetData {
    private Set<ProductOnBudget> products;
    private Set<Supplier> suppliers;
    private String observations;
}
