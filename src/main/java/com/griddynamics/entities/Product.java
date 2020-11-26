package com.griddynamics.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
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


    public Product(Integer id, String name, Double price, String description, String brand, String image, List<Category> category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.category = category;
    }
}
