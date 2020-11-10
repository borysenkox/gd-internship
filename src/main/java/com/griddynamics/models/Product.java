package com.griddynamics.models;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String brand;
    private String image;
}
