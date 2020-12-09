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
            log.error("Products is not exists");
        }

        log.info("List of products returned successfully");

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) throws ServiceException {

        log.info("Trying get product with id = {}", id);

        ProductDTO product = productService.getById(id);

        if (product == null) {
            log.error("Product with id = {} doesn't exists", id);
        }

        log.info("Product with id = {} returned successfully", id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product) throws ServiceException {

        log.info("Trying to save product to database");

        ProductDTO addedProduct = productService.save(product);

        log.info("Product saved successfully");

        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> editProductById(@RequestBody ProductDTO product) throws ServiceException {

        log.info("Trying to update product");

        ProductDTO updatedProduct = productService.update(product);

        log.info("Product updated successfully");

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Integer id) throws ServiceException {

        log.info("Trying to delete product with id = {}", id);

        productService.deleteById(id);

        log.info("Product with id = {} deleted successfully", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
