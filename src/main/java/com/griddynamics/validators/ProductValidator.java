package com.griddynamics.validators;

import com.griddynamics.dto.ProductDTO;
import com.griddynamics.exceptions.BadIdException;
import com.griddynamics.exceptions.ProductNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductValidator implements Validator<ProductDTO> {

    private static final String LOGGER_TEMPLATE = "%s is %s";

    private static final String PRODUCT_DTO = "ProductDTO";

    private static final String FIELD_NAME = "name";

    private static final String FIELD_PRICE = "price";

    private static final String VALUE_NULL = "null";

    private static final String LESS_ZERO = "less than 0";

    @Override
    public void validateDTO(ProductDTO productDTO) {

        ProductNotFoundException productNotFoundException = new ProductNotFoundException();

        if (productDTO == null) {
            log.error(String.format(LOGGER_TEMPLATE, PRODUCT_DTO, VALUE_NULL), productNotFoundException);
            throw productNotFoundException;
        }

        if (productDTO.getName() == null) {
            log.error(String.format(LOGGER_TEMPLATE, FIELD_NAME, VALUE_NULL), productNotFoundException);
            throw productNotFoundException;
        }

        if (productDTO.getPrice() == null) {
            log.error(String.format(LOGGER_TEMPLATE, FIELD_PRICE, VALUE_NULL), productNotFoundException);
            throw productNotFoundException;

        } else if (productDTO.getPrice() < 0) {
            log.error(String.format(LOGGER_TEMPLATE, FIELD_PRICE, LESS_ZERO), productNotFoundException);
            throw productNotFoundException;
        }
    }

    @Override
    public void validateId(Integer id) {

        BadIdException badIdException = new BadIdException();

        if (id == null) {
            log.error("Product with id = {} is {}", VALUE_NULL, id, badIdException);
            throw badIdException;
        } else if (id < 0) {
            log.error("Product id can't be {}. Provided {}", LESS_ZERO, id, badIdException);
            throw badIdException;
        }
    }
}
