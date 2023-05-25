package ua.malysh.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products",
        indexes = {
                @Index(name = "idx_products_product_name", columnList = "product_name")},
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_products_product_name", columnNames = "product_name")})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id",
            nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "product_name",
            nullable = false,
            unique = true)
    private String name;

    @Column(name = "product_measure",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private Measure measure;

    @Column(name = "product_category",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(String name,
                   Category category,
                   Measure measure) {
        this.name = name;
        this.category = category;
        this.measure = measure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

