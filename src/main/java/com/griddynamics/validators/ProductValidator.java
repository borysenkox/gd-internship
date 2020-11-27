package com.griddynamics.validators;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator implements Validator<ProductDTO> {

    private static final String EXCEPTION_TEMPLATE = "%s cannot have field '%s' with value %s.";

    private static final String PRODUCT_DTO = "ProductDTO";

    private static final String FIELD_NAME = "name";

    private static final String FIELD_PRICE = "price";

    private static final String VALUE_NULL = "null";

    @Override
    public void validateDTO(ProductDTO productDTO) throws ValidationException {
        if (productDTO == null) {
            throw new ValidationException("ProductDTO cannot be null.");
        }

        Integer id = productDTO.getId();

        if (productDTO.getName() == null) {
            throw new ValidationException(String.format(EXCEPTION_TEMPLATE, PRODUCT_DTO, FIELD_NAME, VALUE_NULL));
        }

        if (productDTO.getPrice() == null) {
            throw new ValidationException(String.format(EXCEPTION_TEMPLATE, PRODUCT_DTO, FIELD_PRICE, VALUE_NULL));
        }
        else if(productDTO.getPrice() < 0) {
            throw new ValidationException(String.format(EXCEPTION_TEMPLATE, PRODUCT_DTO, FIELD_PRICE, "less than 0"));
        }
    }

    @Override
    public void validateId(Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID of the object cannot be null.");
        } else if (id < 0) {
            throw new ValidationException("ID of the object cannot be less than 0.");
        }
    }
}
