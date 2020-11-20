package com.griddynamics.dto;

import com.griddynamics.entities.Category;
import lombok.Data;

import java.util.Objects;


@Data
public class CategoryDTO extends AbstractDTO {

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

    @Override
    public boolean equals(Object o) {
        if (id != null) return super.equals(o);
        if (this == o) return true;
        if (!(o instanceof CategoryDTO)) return false;
        if (!super.equals(o)) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        if (id != null) return super.hashCode();

        return Objects.hash(super.hashCode(), name, parent);
    }
}
