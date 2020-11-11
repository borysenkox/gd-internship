package com.griddynamics.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public Product() {
    }

    public Product(String name, Double price, String description, String brand, String image, List<Category> category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", id=" + id +
                '}';
    }
}
