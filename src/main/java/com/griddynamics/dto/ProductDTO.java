package com.griddynamics.dto;

import com.griddynamics.entities.Category;
import com.griddynamics.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO extends AbstractDTO {

    private String name;

    private Double price;

    private String description;

    private String brand;

    private String image;

    private List<CategoryDTO> categoryDTOList;

    public ProductDTO(Integer id, String name, Double price, String description, String brand, String image,
                      List<CategoryDTO> categoryDTOList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.image = image;

        if (categoryDTOList != null) {
            this.categoryDTOList = new ArrayList<>(categoryDTOList);
        } else {
            this.categoryDTOList = new ArrayList<>();
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
