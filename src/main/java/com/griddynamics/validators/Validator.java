package com.griddynamics.validators;

import com.griddynamics.dto.AbstractDTO;
import com.griddynamics.entities.AbstractEntity;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateEntity(AbstractEntity abstractEntity) {
        if (abstractEntity == null) {
            throw new IllegalArgumentException("Entity object cannot be null.");
        }

        Integer id = abstractEntity.getId();

        validateId(id);
    }

    public void validateDTO(AbstractDTO abstractDTO) {
        if (abstractDTO == null) {
            throw new IllegalArgumentException("DTO object cannot be null.");
        }

        Integer id = abstractDTO.getId();

        validateId(id);
    }

    public void validateId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID of the object cannot be null.");
        } else if (id < 0) {
            throw new IllegalArgumentException("ID of the object cannot be less than 0.");
        }
    }
}
