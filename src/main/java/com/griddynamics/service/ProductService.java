package com.griddynamics.service;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Product;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.mappers.ProductMapper;
import com.griddynamics.repositories.ProductRepository;
import com.griddynamics.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final Validator<ProductDTO> validator;

    @Autowired
    ProductService(ProductRepository productRepository, ProductMapper productMapper,
                   Validator<ProductDTO> validator) {

        this.productRepository = productRepository;

        this.productMapper = productMapper;

        this.validator = validator;
    }

    public List<ProductDTO> findAll() {

        Iterable<Product> productIterable = productRepository.findAll();

        return productMapper.mapDTOList(productIterable);
    }

    public ProductDTO getById(Integer id) throws ServiceException {

        validator.validateId(id);

        Optional<Product> optProduct = productRepository.findById(id);

        ProductDTO productDTO = null;

        if (optProduct.isPresent()) {
            productDTO = productMapper.mapDTO(optProduct.get());
        }

        return productDTO;
    }

    public ProductDTO save(ProductDTO productDTO) throws ServiceException {

        validator.validateDTO(productDTO);

        Product product = productMapper.mapEntity(productDTO);

        product = productRepository.save(product);

        return productMapper.mapDTO(product);
    }

    public ProductDTO update(ProductDTO productDTO) throws ServiceException {

        validator.validateId(productDTO.getId());

        Optional<Product> optProduct = productRepository.findById(productDTO.getId());

        if (!optProduct.isPresent()) {
            throw new ServiceException("Cannot update product. There is wrong argument product id OR such " +
                    "element is not present in the database.");
        }

        Product product = optProduct.get();

        productMapper.mapUpdate(productDTO, product);

        product = productRepository.save(product);

        return productMapper.mapDTO(product);
    }

    public void deleteById(Integer id) throws ServiceException {

        validator.validateId(id);

        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            throw new ServiceException(
                    String.format("Product with %d is not present in the database.", id));
        }

        productRepository.deleteById(id);
    }

}
