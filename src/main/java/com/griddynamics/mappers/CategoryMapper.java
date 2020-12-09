package com.griddynamics.mappers;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends EntityMapper<Category, CategoryDTO> {
    @Override
    public CategoryDTO mapDTO(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();

        mapDTO(category, categoryDTO);

        return categoryDTO;
    }

    @Override
    public Category mapEntity(CategoryDTO categoryDTO) {

        Category category = new Category();

        mapEntity(categoryDTO, category);

        return category;
    }

    @Override
    public CategoryDTO mapDTO(Category category, CategoryDTO categoryDTO) {

        if (category == null || categoryDTO == null) {
            return null;
        }

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setParent(category.getParent());

        return categoryDTO;
    }

    @Override
    public Category mapEntity(CategoryDTO categoryDTO, Category category) {

        if (categoryDTO == null || category == null) {
            return null;
        }

        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setParent(categoryDTO.getParent());

        return category;
    }

    @Override
    public Category mapUpdate(CategoryDTO categoryDTO, Category category) {

        category.setName(categoryDTO.getName() == null ? category.getName() : categoryDTO.getName());
        category.setParent(categoryDTO.getParent() == null ? category.getParent() : categoryDTO.getParent());

        return category;
    }
}
