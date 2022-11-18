package com.budgetmanager.domain;

import jakarta.persistence.Column;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "read_only_products")
@Data
public class ReadOnlyProduct {
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "category")
    private String category;
}
