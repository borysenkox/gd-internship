package com.griddynamics.dto;

import com.griddynamics.entities.Category;
import com.griddynamics.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (id != null) return super.equals(o);
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        if (!super.equals(o)) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(image, that.image) &&
                Objects.equals(categoryDTOList, that.categoryDTOList);
    }

    @Override
    public int hashCode() {
        if (id != null) return super.hashCode();

        return Objects.hash(super.hashCode(), name, price, description, brand, image, categoryDTOList);
    }
}
