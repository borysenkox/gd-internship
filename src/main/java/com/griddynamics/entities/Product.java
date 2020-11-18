package com.griddynamics.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "Product")
@Table(name = "product")
public class Product extends AbstractEntity {

    private String name;

    private Double price;

    private String description;

    private String brand;

    private String image;

    public Product() { }

    public Product(String name, Double price, String description, String brand, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;

    }

}
