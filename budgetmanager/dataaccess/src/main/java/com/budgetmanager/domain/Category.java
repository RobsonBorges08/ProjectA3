package com.budgetmanager.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToOne(mappedBy = "category")
    private Product product;

    @ManyToMany(mappedBy = "categories")
    private Set<Supplier> suppliers;
}
