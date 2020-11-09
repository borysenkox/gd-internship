package com.griddynamics.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ProductController {

    @GetMapping("/product")
    public String getAllProducts() {
        return "In getAllProducts()";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable("id") int id) {
        return "In getProductById()";
    }

    @PostMapping("/product")
    public String addNewProduct() {
        return "In addNewProduct()";
    }

    @PutMapping("/product/{id}")
    public String editProductById(@PathVariable("id") int id) {
        return "In editProductById()";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProductById(@PathVariable("id") int id) {
        return "In deleteProductById()";
    }
}
