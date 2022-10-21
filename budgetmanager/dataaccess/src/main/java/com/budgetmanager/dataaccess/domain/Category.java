package com.budgetmanager.dataaccess.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.OneToOne;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToOne(mappedBy = "product")
    private Product product;
}
