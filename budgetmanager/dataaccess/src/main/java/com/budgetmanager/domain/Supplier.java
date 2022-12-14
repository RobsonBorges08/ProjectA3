package com.budgetmanager.domain;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "companyName")
    private String companyName;
    
    @Column(name = "tradingName")
    private String tradingName;
    
    @Column(unique = true)
    private String employersIdentificationNumber; //CNPJ
    
    private String email;
    private String zipCode;
    private String street;
    private int buildingNumber;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;

    @ManyToMany
    @JoinTable(
            name = "supplier_category",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
