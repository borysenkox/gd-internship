package com.griddynamics.validators;

import com.griddynamics.dto.AbstractDTO;
import com.griddynamics.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class GeneralValidator implements Validator<AbstractDTO> {

    public void validateDTO(AbstractDTO abstractDTO) throws ValidationException {
        if (abstractDTO == null) {
            throw new ValidationException("DTO object cannot be null.");
        }
    }

    public void validateId(Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID of the object cannot be null.");
        } else if (id < 0) {
            throw new ValidationException("ID of the object cannot be less than 0.");
        }
    }
}
