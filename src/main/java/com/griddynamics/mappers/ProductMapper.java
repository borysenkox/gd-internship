package com.griddynamics.mappers;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Category;
import com.griddynamics.entities.Product;
import com.griddynamics.exceptions.MappingException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductMapper extends EntityMapper<Product, ProductDTO> {

    @Override
    public ProductDTO mapDTO(Product product, ProductDTO productDTO) {
        if (product == null || productDTO == null) {
            throw new MappingException("One of arguments is null.");
        }
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setBrand(product.getBrand());
        productDTO.setImage(product.getImage());

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        productDTO.setCategoryDTOList(categoryDTOList);

        List<Category> categoryList = product.getCategory();

        CategoryMapper categoryMapper = new CategoryMapper();

        categoryList.forEach(category -> categoryDTOList.add(categoryMapper.mapDTO(category)));

        return productDTO;
    }

    @Override
    public Product mapEntity(ProductDTO productDTO, Product product) {
        if (productDTO == null) {
            throw new MappingException("One of arguments is null.");
        }

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        if (productDTO.getCategoryDTOList() != null) {
            categoryDTOList = new ArrayList<>(productDTO.getCategoryDTOList());
        }

        List<Category> categoryList = new ArrayList<>();

        CategoryMapper categoryMapper = new CategoryMapper();

        categoryDTOList.forEach(categoryDTO -> 
            categoryList.add(categoryMapper.mapEntity(categoryDTO)));

        product.setCategory(categoryList);

        return product;
    }

    @Override
    public Product mapEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new MappingException("ProductDTO is null.");
        }
        return mapEntity(productDTO, new Product());
    }

    @Override
    public ProductDTO mapDTO(Product product) {
        if (product == null) {
            throw new MappingException("Product is null.");
        }
        return mapDTO(product, new ProductDTO());
    }

    @Override
    public List<Product> mapList(Iterable<Product> iterable) {

        List<Product> list = new ArrayList<>();

        iterable.forEach(list::add);

        return list;
    }

    @Override
    public List<ProductDTO> mapDTOList(Iterable<Product> iterable) {

        List<ProductDTO> dtoList;

        List<Product> productList = mapList(iterable);

        dtoList = mapDTOList(productList);

        return dtoList;
    }

    @Override
    public Product mapUpdate(ProductDTO productDTO, Product product) {

        product.setName(productDTO.getName() == null ? product.getName() : productDTO.getName());
        product.setPrice(productDTO.getPrice() == null ? product.getPrice() : productDTO.getPrice());
        product.setBrand(productDTO.getBrand() == null ? product.getBrand() : productDTO.getBrand());

        product.setDescription(productDTO.getDescription() == null ?
                product.getDescription() : productDTO.getDescription());

        product.setImage(productDTO.getImage() == null ? product.getImage() : productDTO.getImage());

        CategoryMapper categoryMapper = new CategoryMapper();

        List<Category> categoryList = null;

        if (productDTO.getCategoryDTOList() != null) {
            categoryList = categoryMapper.mapList(productDTO.getCategoryDTOList());
        }

        product.setCategory(categoryList == null ? product.getCategory() : categoryList);

        return product;
    }
}
