package com.griddynamics.mappers;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.entities.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setParent(category.getParent());

        return categoryDTO;
    }

    @Override
    public Category mapEntity(CategoryDTO categoryDTO, Category category) {

        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setParent(categoryDTO.getParent());

        return category;
    }


    @Override
    public List<Category> mapList(Iterable<Category> iterable) {

        List<Category> categoryList = new ArrayList<>();

        iterable.forEach(categoryList::add);

        return categoryList;
    }

    @Override
    public List<CategoryDTO> mapDTOList(Iterable<Category> iterable) {

        List<CategoryDTO> listDTO;

        List<Category> categoryList = mapList(iterable);

        listDTO = categoryList.stream().map(this::mapDTO).collect(Collectors.toList());

        categoryList.forEach(product -> listDTO.add(mapDTO(product)));

        return listDTO;
    }

    @Override
    public Category mapUpdate(CategoryDTO categoryDTO, Category category) {
        return null;
    }
}
