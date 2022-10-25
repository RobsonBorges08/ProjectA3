package com.budgetmanager.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class BudgetRequest implements Serializable {
    private Product product;
    private Supplier supplier;
    private String observations;
}
