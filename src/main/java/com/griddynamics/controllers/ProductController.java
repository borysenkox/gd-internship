package com.griddynamics.controllers;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.findAll();

        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {

        ProductDTO product;

        try {
            product = productService.getById(id);
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product) {

        ProductDTO addedProduct;

        try {
            addedProduct = productService.save(product);
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> editProductById(@RequestBody ProductDTO product) {

        ProductDTO updatedProduct;

        try {
            updatedProduct = productService.update(product);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Integer id) {

        try {
            productService.deleteById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
