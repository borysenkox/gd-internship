package com.griddynamics.dto;

import com.griddynamics.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO extends AbstractDTO {

    private String name;

    private Category parent;

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
