package com.griddynamics.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Product")
@Table(name = "product")
public class Product extends AbstractEntity {

    private String name;

    private Double price;

    private String description;

    private String brand;

    private String image;

    @OneToMany(
            cascade = CascadeType.ALL)
    private List<Category> category = new ArrayList<>();

    public Product() { }

    public Product(String name, Double price, String description, String brand, String image, List<Category> category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.category = category;
    }

}
