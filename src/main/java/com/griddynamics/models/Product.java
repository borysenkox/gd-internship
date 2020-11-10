package com.griddynamics.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    private int id;
    private String name;
    private double price;
    private String description;
    private String brand;
    private String image;
}
