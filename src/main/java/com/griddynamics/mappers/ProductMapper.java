package com.griddynamics.mappers;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Category;
import com.griddynamics.entities.Product;
import com.griddynamics.exceptions.MappingException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductMapper extends EntityMapper<Product, ProductDTO> {

    private static CategoryMapper categoryMapper;

    private synchronized static CategoryMapper getCategoryMapper() {
        if (categoryMapper == null) {
            categoryMapper = new CategoryMapper();
        }
        return categoryMapper;
    }

    @Override
    public ProductDTO mapDTO(Product product, ProductDTO productDTO) throws MappingException {
        if (product == null || productDTO == null) {
            throw new MappingException("One of the arguments is null.");
        }
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setBrand(product.getBrand());
        productDTO.setImage(product.getImage());

        List<Category> categoryList = product.getCategory();
        List<CategoryDTO> categoryDTOList;

        if (categoryList != null) {
            categoryDTOList = categoryList.stream().map(getCategoryMapper()::mapDTO).collect(Collectors.toList());
        } else {
            categoryDTOList = new ArrayList<>();
        }

        productDTO.setCategoryDTOList(categoryDTOList);

        return productDTO;
    }

    @Override
    public Product mapEntity(ProductDTO productDTO, Product product) throws MappingException {
        if (productDTO == null) {
            throw new MappingException("One of the arguments is null.");
        }

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());

        List<CategoryDTO> categoryDTOList = productDTO.getCategoryDTOList();
        List<Category> categoryList;

        if (categoryDTOList != null) {
            categoryList = categoryDTOList.stream().map(getCategoryMapper()::mapEntity).collect(Collectors.toList());
        } else {
            categoryList = new ArrayList<>();
        }

        product.setCategory(categoryList);

        return product;
    }

    @Override
    public Product mapEntity(ProductDTO productDTO) throws MappingException {
        if (productDTO == null) {
            throw new MappingException("ProductDTO is null.");
        }
        return mapEntity(productDTO, new Product());
    }

    @Override
    public ProductDTO mapDTO(Product product) throws MappingException {
        if (product == null) {
            throw new MappingException("Product is null.");
        }
        return mapDTO(product, new ProductDTO());
    }

    @Override
    public Product mapUpdate(ProductDTO productDTO, Product product) throws MappingException {

        if (productDTO == null || product == null) {
            throw new MappingException("Argument(-s) cannot be null.");
        }

        product.setName(productDTO.getName() == null ? product.getName() : productDTO.getName());
        product.setPrice(productDTO.getPrice() == null ? product.getPrice() : productDTO.getPrice());
        product.setBrand(productDTO.getBrand() == null ? product.getBrand() : productDTO.getBrand());

        product.setDescription(productDTO.getDescription() == null ?
                product.getDescription() : productDTO.getDescription());

        product.setImage(productDTO.getImage() == null ? product.getImage() : productDTO.getImage());

        List<Category> categoryList = null;

        if (productDTO.getCategoryDTOList() != null) {
            categoryList = getCategoryMapper().mapList(productDTO.getCategoryDTOList());
        }

        product.setCategory(categoryList == null ? product.getCategory() : categoryList);

        return product;
    }
}
