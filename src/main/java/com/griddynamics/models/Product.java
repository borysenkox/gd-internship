package com.griddynamics.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String brand;
    private String image;
}
