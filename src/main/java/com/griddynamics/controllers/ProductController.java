package com.griddynamics.controllers;

import com.griddynamics.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping
    public String hello() {
        return "Hello Product";
    }

    @PostMapping
    public String goodBuy(@RequestBody Product product) {
        return product.toString();
    }
}
