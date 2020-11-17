package com.griddynamics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private String description;
    private String brand;
    private String image;
}
