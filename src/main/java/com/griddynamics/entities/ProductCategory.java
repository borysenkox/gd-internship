package com.griddynamics.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "ProductCategory")
@Table(name = "product_category")
public class ProductCategory extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}

