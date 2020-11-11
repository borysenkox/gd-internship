package com.griddynamics.entities;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Category")
@Table(name = "category")
public class Category extends AbstractEntity {

    private String name;

    @OneToOne
    private Category parent;

    public Category() {
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", id=" + id +
                '}';
    }
}
