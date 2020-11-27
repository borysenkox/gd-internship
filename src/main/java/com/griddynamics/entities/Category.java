package com.griddynamics.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Category")
@Table(name = "category")
public class Category extends AbstractEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Category parent;

    public Category(Integer id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}
