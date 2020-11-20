package com.griddynamics.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "Product")
@Table(name = "product")
public class Product extends AbstractEntity {

    private String name;

    private Double price;

    private String description;

    private String brand;

    private String image;

    @ManyToMany(
            cascade = CascadeType.DETACH)
    private List<Category> category = new ArrayList<>();

    public Product() { }

    public Product(Integer id, String name, Double price, String description, String brand, String image, List<Category> category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.category = category;
    }

    public Product(String name, Double price, String description, String brand, String image, List<Category> category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (id != null) return super.equals(o);
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(description, product.description) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(image, product.image) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        if (id != null) return super.hashCode();

        return Objects.hash(super.hashCode(), name, price, description, brand, image, category);
    }
}
