package com.griddynamics.mappers;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.dto.ProductDTO;
import com.griddynamics.entities.Category;
import com.griddynamics.entities.Product;
import com.griddynamics.exceptions.MappingException;

import java.util.ArrayList;
import java.util.List;


public class ProductMapper extends EntityMapper<Product, ProductDTO> {

    @Override
    public Product mapEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new MappingException("ProductDTO is null.");
        }
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        if (productDTO.getCategoryDTOList() != null) {
            categoryDTOList = new ArrayList<>(productDTO.getCategoryDTOList());
        }

        List<Category> categoryList = new ArrayList<>();

        categoryDTOList.forEach(categoryDTO -> {
            Category category = new Category();

            category.setId(categoryDTO.getId());
            category.setName(categoryDTO.getName());
            category.setParent(category.getParent());

            categoryList.add(category);
        });

        product.setCategory(categoryList);

        return product;
    }

    @Override
    public ProductDTO mapDTO(Product product) {
        if (product == null) {
            throw new MappingException("Product is null.");
        }

        return new ProductDTO(product);
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
