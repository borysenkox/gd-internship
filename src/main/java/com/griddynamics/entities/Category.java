package com.griddynamics.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "Category")
@Table(name = "category")
public class Category extends AbstractEntity {

    private String name;

    @OneToOne
    private Category parent;

    public Category() { }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

}
