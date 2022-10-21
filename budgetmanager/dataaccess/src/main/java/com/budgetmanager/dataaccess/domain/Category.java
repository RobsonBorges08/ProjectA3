package com.budgetmanager.dataaccess.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToOne(mappedBy = "category")
    private Product product;
    
    @ManyToMany(mappedBy = "categories")
    private Set<Supplier> suppliers;
}
