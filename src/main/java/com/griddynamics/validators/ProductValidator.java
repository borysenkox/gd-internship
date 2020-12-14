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

    private static final String PRODUCT = "Product";

    private static final String FIELD_NAME = "name";

    private static final String FIELD_PRICE = "price";

    private static final String VALUE_NULL = "null";

    private static final String LESS_ZERO = "less than 0";

    @Override
    public void validateDTO(ProductDTO productDTO) {

        if (productDTO == null) {
            String message = String.format(LOGGER_TEMPLATE, PRODUCT_DTO, VALUE_NULL);

            ProductNotFoundException productNotFoundException = new ProductNotFoundException(message);

            log.error(message, productNotFoundException);

            throw productNotFoundException;
        }

        if (productDTO.getName() == null) {
            String message = String.format(LOGGER_TEMPLATE, FIELD_NAME, VALUE_NULL);

            ProductNotFoundException productNotFoundException = new ProductNotFoundException(message);

            log.error(message, productNotFoundException);

            throw productNotFoundException;
        }

        if (productDTO.getPrice() == null) {
            String message = String.format(LOGGER_TEMPLATE, FIELD_PRICE, VALUE_NULL);

            ProductNotFoundException productNotFoundException = new ProductNotFoundException(message);

            log.error(message, productNotFoundException);

            throw productNotFoundException;

        } else if (productDTO.getPrice() < 0) {
            String message = String.format(LOGGER_TEMPLATE, FIELD_PRICE, LESS_ZERO);

            ProductNotFoundException productNotFoundException = new ProductNotFoundException(message);

            log.error(message, productNotFoundException);

            throw productNotFoundException;
        }
    }

    @Override
    public void validateId(Integer id) {
        if (id == null) {
            String message = String.format("%s with id = %d is %s", PRODUCT, id, VALUE_NULL);

            BadIdException badIdException = new BadIdException(message);

            log.error(message, badIdException);

            throw badIdException;
        } else if (id < 0) {
            String message = String.format("%s id can't be %s. Provided %d", PRODUCT, LESS_ZERO, id);

            BadIdException badIdException = new BadIdException(message);

            log.error(message, badIdException);

            throw badIdException;
        }
    }
}
