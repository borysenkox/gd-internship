package com.griddynamics.validators;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.exceptions.BadIdException;
import com.griddynamics.exceptions.ProductNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductValidator implements Validator<ProductDTO> {

    private static final String EXCEPTION_TEMPLATE = "%s cannot have field '%s' with value %s.";

    private static final String PRODUCT_DTO = "ProductDTO";

    private static final String FIELD_NAME = "name";

    private static final String FIELD_PRICE = "price";

    private static final String VALUE_NULL = "null";

    @Override
    public void validateDTO(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new ProductNotFoundException("ProductDTO cannot be null.");
        }

        if (productDTO.getName() == null) {
            throw new ProductNotFoundException(String.format(EXCEPTION_TEMPLATE, PRODUCT_DTO, FIELD_NAME, VALUE_NULL));
        }

        if (productDTO.getPrice() == null) {
            throw new ProductNotFoundException(String.format(EXCEPTION_TEMPLATE, PRODUCT_DTO, FIELD_PRICE, VALUE_NULL));
        }

        else if(productDTO.getPrice() < 0) {
            throw new ProductNotFoundException(String.format(EXCEPTION_TEMPLATE, PRODUCT_DTO, FIELD_PRICE, "less than 0"));
        }
    }

    @Override
    public void validateId(Integer id) {

        BadIdException e = new BadIdException();

        if (id == null) {
            log.error("Product with id = {} is null", id, e);
            throw new BadIdException();
        } else if (id < 0) {
            log.error("Product id can't be less then 0. Provided {}", id, e);
            throw new BadIdException();
        }
    }
}
