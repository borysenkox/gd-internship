package com.griddynamics.service;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Product;
import com.griddynamics.mappers.ProductMapper;
import com.griddynamics.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    ProductMapper productMapper;

    @Autowired
    ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;

        productMapper = new ProductMapper();
    }

    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOList;

        Iterable<Product> productIterable = productRepository.findAll();

        productDTOList = productMapper.mapDTOList(productIterable);

        return productDTOList;
    }

    public ProductDTO getById(Integer id) {
        if (id == null || id < 0) {
            return null;
        }

        Optional<Product> optProduct = productRepository.findById(id);

        ProductDTO productDTO = null;

        if (optProduct.isPresent()) {
            productDTO = new ProductDTO(optProduct.get());
        }

        return productDTO;
    }

    public void save(ProductDTO productDTO) {
        Product product;

        product = productMapper.mapEntity(productDTO);

        productRepository.save(product);
    }

    public void update(ProductDTO productDTO) {
        if (productDTO == null || productDTO.getId() < 0) {
            return;
        }

        Optional<Product> optProduct = productRepository.findById(productDTO.getId());

        if (!optProduct.isPresent()) {
            return;
        }

        Product product = optProduct.get();

        productMapper.mapUpdate(productDTO, product);

        productRepository.save(product);
    }

    public void deleteById(Integer id) {
        if (id == null || id < 0) {
            return;
        }

        productRepository.deleteById(id);
    }

}
