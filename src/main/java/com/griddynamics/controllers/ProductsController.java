package com.griddynamics.controllers;

import com.griddynamics.models.Product;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {
    @PutMapping("/product")
    public String hello() {
        return "Hello Product";
    }

    @PutMapping("/product")
    public String goodBuy(@RequestBody Product product) {
        return product.toString();
    }

    @PutMapping("/product")
    public String addNewProduct(@RequestBody Product product) {
        return null;
    }
}
