package com.budgetmanager.domain;

import javax.persistence.Column;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
