package com.budgetmanager.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "products")
    private Set<BudgetRequest> budgetRequests;
}
