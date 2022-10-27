module com.budgetmanager.dataaccess {
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.persistence;
    requires lombok;
    requires com.alphateam.budgetmanager.core;
    
    exports com.budgetmanager;
    exports com.budgetmanager.domain;
}
