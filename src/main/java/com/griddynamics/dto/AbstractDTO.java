package com.griddynamics.dto;

import com.griddynamics.entities.AbstractEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public abstract class AbstractDTO implements Serializable {

    protected Integer id;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        AbstractDTO dto = (AbstractDTO) obj;

        return id.equals(dto.id);
    }


}
