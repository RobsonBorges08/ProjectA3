package com.budgetmanager.domain;

<<<<<<< HEAD
import java.util.Set;
=======
>>>>>>> c1429ef68d5ce8337743f5e8b68ee9071ccaafd3
import javax.persistence.CascadeType;
import javax.persistence.Column;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
>>>>>>> c1429ef68d5ce8337743f5e8b68ee9071ccaafd3
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
<<<<<<< HEAD
    
    @OneToMany(mappedBy = "products")
    private Set<BudgetRequest> budgetRequests;
=======
>>>>>>> c1429ef68d5ce8337743f5e8b68ee9071ccaafd3
}
