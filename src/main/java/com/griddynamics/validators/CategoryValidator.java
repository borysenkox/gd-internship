package com.griddynamics.validators;

import com.griddynamics.dto.CategoryDTO;
import com.griddynamics.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator implements Validator<CategoryDTO> {

    private static final String EXCEPTION_TEMPLATE = "%s cannot have field '%s' with value %s.";

    private static final String CATEGORY_DTO = "CategoryDTO";

    private static final String FIELD_NAME = "name";

    private static final String VALUE_NULL = "null";

    @Override
    public void validateDTO(CategoryDTO categoryDTO) throws ValidationException {
        if (categoryDTO == null) {
            throw new ValidationException(String.format("%s object cannot be %s.", CATEGORY_DTO, VALUE_NULL));
        }

        Integer id = categoryDTO.getId();

        if (categoryDTO.getName() == null) {
            throw new ValidationException(String.format(EXCEPTION_TEMPLATE, CATEGORY_DTO, FIELD_NAME, VALUE_NULL));
        }
    }

    @Override
    public void validateId(Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID of the Category object cannot be null.");
        } else if (id < 0) {
            throw new ValidationException("ID of the Category object cannot be less than 0.");
        }
    }
}
