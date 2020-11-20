package com.griddynamics.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity(name = "Category")
@Table(name = "category")
public class Category extends AbstractEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Category parent;

    public Category() { }

    public Category(Integer id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (id != null) return super.equals(o);
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(parent, category.parent);
    }

    @Override
    public int hashCode() {
        if (id != null) return super.hashCode();

        return Objects.hash(super.hashCode(), name, parent);
    }
}
