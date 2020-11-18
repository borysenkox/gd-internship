package com.griddynamics.dto;

import com.griddynamics.entities.Category;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    private Integer id;

    private String name;

    private Category parent;

    public CategoryDTO() { }

    public CategoryDTO(Integer id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
        parent = category.getParent();
    }
}
