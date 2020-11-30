package com.griddynamics.controllers;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Start loading list of products");
        List<ProductDTO> products = productService.findAll();

        if (products == null) {
            log.error("Product is not exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("List of products returned successfully");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {

        ProductDTO product;

        try {
            log.info("Trying get product by id " + id);
            product = productService.getById(id);
        } catch (ServiceException ex) {
            log.error("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (product == null) {
            log.error("Product is not exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Product with id " + id + " returned successfully");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product) {

        ProductDTO addedProduct;

        try {
            log.info("Trying to save product to DB");
            addedProduct = productService.save(product);
        } catch (ServiceException ex) {
            log.error("Can't save product");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Product saved successfully");
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> editProductById(@RequestBody ProductDTO product) {

        ProductDTO updatedProduct;

        try {
            log.info("Trying to update product");
            updatedProduct = productService.update(product);
        } catch (ServiceException e) {
            log.error("Can't update product");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Product updated successfully");
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Integer id) {

        try {
            log.info("Trying to delete product with id " + id);
            productService.deleteById(id);
        } catch (ServiceException e) {
            log.error("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Product with id " + id + " deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
