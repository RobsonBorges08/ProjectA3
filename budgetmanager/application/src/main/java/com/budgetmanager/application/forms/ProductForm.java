/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.application.forms;

import com.budgetmanager.domain.product.ProductData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Data;

@Data
public class ProductForm {
    private TextField descriptionField;
    private ComboBox<String> categoryComboBox;

    public ProductData toProductData() {
        ProductData data = new ProductData();
        
        String description = descriptionField.getText();
        data.setDescription(description);
        
        String categoryName = categoryComboBox.getValue();
        data.setCategoryName(categoryName);
        
        return data;
    }
    
}
