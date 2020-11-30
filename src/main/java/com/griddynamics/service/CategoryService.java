package com.griddynamics.service;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.entities.Category;
import com.griddynamics.exceptions.ServiceException;
import com.griddynamics.mappers.EntityMapper;
import com.griddynamics.repositories.CategoryRepository;
import com.griddynamics.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final Validator<CategoryDTO> validator;

    private final EntityMapper<Category, CategoryDTO> categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, Validator<CategoryDTO> validator,
                           EntityMapper<Category, CategoryDTO> categoryMapper) {

        this.categoryRepository = categoryRepository;

        this.validator = validator;

        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> findAll() {

        Iterable<Category> categoryIterable = categoryRepository.findAll();

        return categoryMapper.mapDTOList(categoryIterable);
    }

    public CategoryDTO getById(Integer id) throws ServiceException {

        validator.validateId(id);

        Optional<Category> optCategory = categoryRepository.findById(id);

        CategoryDTO categoryDTO = null;

        if (optCategory.isPresent()) {
            categoryDTO = categoryMapper.mapDTO(optCategory.get());
        }

        return categoryDTO;
    }

    public CategoryDTO save(CategoryDTO categoryDTO) throws ServiceException {

        validator.validateDTO(categoryDTO);

        Category category = categoryMapper.mapEntity(categoryDTO);

        category = categoryRepository.save(category);

        return categoryMapper.mapDTO(category);
    }

    public CategoryDTO update(CategoryDTO categoryDTO) throws ServiceException {

        validator.validateId(categoryDTO.getId());

        Optional<Category> optCategory = categoryRepository.findById(categoryDTO.getId());

        if (!optCategory.isPresent()) {
            throw new ServiceException("Cannot update category. There is wrong argument category id OR such " +
                    "element is not present in the database.");
        }

        Category category = optCategory.get();

        categoryMapper.mapUpdate(categoryDTO, category);

        category = categoryRepository.save(category);

        return categoryMapper.mapDTO(category);
    }

    public void deleteById(Integer id) throws ServiceException {

        validator.validateId(id);

        Optional<Category> optCategory = categoryRepository.findById(id);

        if (!optCategory.isPresent()) {
            throw new ServiceException(String.format("Product with %d is not present in the database.", id));
        }

        categoryRepository.deleteById(id);
    }
}
