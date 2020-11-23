package com.griddynamics.dto;

import com.griddynamics.entities.Category;
import com.griddynamics.entities.Product;
import com.griddynamics.exceptions.MappingException;
import com.griddynamics.mappers.CategoryMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends AbstractDTO {

    private String name;

    private Double price;

    private String description;

    private String brand;

    private String image;

    private List<CategoryDTO> categoryDTOList;

    public ProductDTO() { }

    public ProductDTO(Integer id, String name, Double price, String description, String brand, String image,
                      List<CategoryDTO> categoryDTOList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;

        this.categoryDTOList = new ArrayList<>();

        if (categoryDTOList != null) {
            this.categoryDTOList = new ArrayList<>(categoryDTOList);
        }
    }

    public ProductDTO(Product product) {
        if (product == null ) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        setId(product.getId());
        setName(product.getName());
        setPrice(product.getPrice());
        setDescription(product.getDescription());
        setBrand(product.getBrand());
        setImage(product.getImage());

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        setCategoryDTOList(categoryDTOList);

        List<Category> categoryList = product.getCategory();

        CategoryMapper categoryMapper = new CategoryMapper();

        if (categoryList != null) {
            categoryList.forEach(category -> categoryDTOList.add(categoryMapper.mapDTO(category)));
        }
    }
}
