package com.budgetmanager.domain;

import lombok.Data;

@Data
public class ProductOnBudget {

    private Product product;
    private double quantity;
    private String unitOfMeasure;

    @Override
    public String toString() {
        int productId = product.getId();
        String productDescription = product.getDescription();
        String quantityToPurchase = String.format("%.2f", quantity);

        return (productId + " - " + productDescription + " >> "
                + quantityToPurchase + " " + unitOfMeasure);
    }
}
