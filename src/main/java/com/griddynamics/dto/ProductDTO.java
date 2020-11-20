package com.griddynamics.dto;

import com.griddynamics.entities.Category;
import com.griddynamics.entities.Product;
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
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        brand = product.getBrand();
        image = product.getImage();

        categoryDTOList = new ArrayList<>();

        List<Category> categoryList = product.getCategory();

        categoryList.forEach(category -> categoryDTOList.add(new CategoryDTO(category)));
    }
}
