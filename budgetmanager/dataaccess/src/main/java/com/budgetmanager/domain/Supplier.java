package com.budgetmanager.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    private String companyName;
    private String tradingName;
    
    @Column(unique = true)
    private String employersIdentificationNumber; //CNPJ
    
    private String email;
    private String zipCode;
    private String street;
    private int buildingNumber;
    private String city;
    private String country;

    @ManyToMany
    @JoinTable(
            name = "supplier_category",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
