package com.budgetmanager.domain;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;

@Data
public class BudgetRequest implements Serializable {
    private Set<ProductOnBudget> products;
    private Supplier supplier;
    private String observations;
}
