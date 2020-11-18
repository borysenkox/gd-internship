package com.griddynamics.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "Category")
@Table(name = "category")
public class Category extends AbstractEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Category parent;

    public Category() { }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

}
