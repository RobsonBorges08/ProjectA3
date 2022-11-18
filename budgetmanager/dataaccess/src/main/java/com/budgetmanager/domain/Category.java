package com.budgetmanager.domain;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NaturalId
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> product;

    @ManyToMany(mappedBy = "categories")
    private Set<Supplier> suppliers;
}
