package com.griddynamics.service;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Product;
import com.griddynamics.mappers.ProductMapper;
import com.griddynamics.repositories.ProductRepository;
import com.griddynamics.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final Validator validator;

    @Autowired
    ProductService(ProductRepository productRepository, ProductMapper productMapper,
                   Validator validator) {

        this.productRepository = productRepository;

        this.productMapper = productMapper;

        this.validator = validator;

    }

    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOList;

        Iterable<Product> productIterable = productRepository.findAll();

        productDTOList = productMapper.mapDTOList(productIterable);

        return productDTOList;
    }

    public ProductDTO getById(Integer id) {

        validator.validateId(id);

        Optional<Product> optProduct = productRepository.findById(id);

        ProductDTO productDTO = new ProductDTO();

        optProduct.ifPresent(product -> productMapper.mapDTO(product, productDTO));

        return productDTO;
    }

    public void save(ProductDTO productDTO) {

        Product product;

        product = productMapper.mapEntity(productDTO);

        product = productRepository.save(product);

        productMapper.mapDTO(product, productDTO);
    }

    public void update(ProductDTO productDTO) {

        validator.validateDTO(productDTO);

        Optional<Product> optProduct = productRepository.findById(productDTO.getId());

        if (!optProduct.isPresent()) {
            throw new NoSuchElementException("Cannot update product. There is wrong argument product id OR such" +
                    "element is not present in the database.");
        }

        Product product = optProduct.get();

        productMapper.mapUpdate(productDTO, product);

        productRepository.save(product);
    }

    public void deleteById(Integer id) {

        validator.validateId(id);

        productRepository.deleteById(id);
    }

}
