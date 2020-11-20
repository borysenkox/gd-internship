package com.griddynamics.validators;

import com.griddynamics.dto.AbstractDTO;
import org.springframework.stereotype.Component;

/**
 * Interface that provides validation of the DTO objects.
 * Mainly used to check state of NOT NULL fields of DTO objects.
 *
 * @param <D> DTO object, subclass of {@link AbstractDTO} that has to be validated
 */
@Component
public interface Validator<D extends AbstractDTO> {

    /**
     * Validates DTO object without validating ID.
     *
     * @param d {@link AbstractDTO} subclass that have to be validated
     */
    void validateDTO(D d);

    /**
     * Validates id of the some object.
     *
     * @param id id of the some object
     */
    void validateId(Integer id);
}
