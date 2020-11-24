package com.griddynamics.mappers;

import com.griddynamics.dto.AbstractDTO;
import com.griddynamics.entities.AbstractEntity;
import com.griddynamics.exceptions.MappingException;
import com.griddynamics.exceptions.ValidationException;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code EntityMapper} - abstract class to map DTO objects to the {@link com.griddynamics.entities.AbstractEntity}
 * subclasses and vice versa.
 *
 * @param <E> class.
 * @param <D> DTO class.
 */

public abstract class EntityMapper<E extends AbstractEntity, D extends AbstractDTO> {


    /**
     * Maps entity {@code E} to its newly created DTO object {@code D}.
     *
     * @param e database entity
     * @return DTO object of entity
     */
    public abstract D mapDTO(E e) throws MappingException;

    /**
     * Maps DTO object of entity {@code D} to newly created database entity {@code E}.
     *
     * @param d DTO object
     * @return database entity
     */
    public abstract E mapEntity(D d) throws MappingException;

    /**
     * Maps entity {@code E} to its DTO object {@code D}.
     *
     * @param e database entity
     * @param d DTO object
     * @return {@code d} object with mapping-state changes.
     */
    public abstract D mapDTO(E e, D d) throws MappingException;

    /**
     * Maps DTO object of entity {@code D} to database entity {@code E}.
     *
     * @param d DTO object
     * @return {@code e} object with mapping-state changes.
     */
    public abstract E mapEntity(D d, E e) throws MappingException;

    /**
     * Maps List of DTO objects {@code D} to List of Entity objects {@code E}.
     * To use this default implementation - {@link EntityMapper#mapEntity(D)} should also be implemented.
     *
     * @param dList {@link List<D>} that used as source of {@code D} objects
     *              for mapping to {@code E} objects.
     * @return object of {@link List<E>} that based on {@code dList}.
     */
    public List<E> mapList(List<D> dList) {

        return dList.stream().map(d -> {
            try {
                return mapEntity(d);
            } catch (MappingException exception) {
                exception.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * Maps List of Entity objects {@code E} to List of DTO objects {@code D}.
     * To use this default implementation - {@link EntityMapper#mapDTO(AbstractEntity)} should also be implemented.
     *
     * @param eList {@link List<AbstractEntity>} that used as source of {@code E} objects
     *              for mapping to {@code D} objects.
     * @return object of {@link List<D>} that based on {@code eList}.
     */
    public List<D> mapDTOList(List<E> eList) {

        return eList.stream().map(e -> {
            try {
                return mapDTO(e);
            } catch (MappingException exception) {
                exception.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * Maps {@link Iterable} from {@link CrudRepository#findAll()} to the {@link List} with {@code E} entities.
     *
     * @param i {@link Iterable}
     * @return {@link List} with {@code E} entities
     */
    public abstract List<E> mapList(Iterable<E> i);

    /**
     * Maps {@link Iterable} from {@link CrudRepository#findAll()} to the {@link List} with {@code D} DTO objects.
     *
     * @param i {@link Iterable}
     * @return {@link List} with {@code D} DTO objects
     */
    public abstract List<D> mapDTOList(Iterable<E> i);

    /**
     * Changes all null-values variables of {@code E} entity to values of {@code D} object.
     * Method mainly used for update operation in CRUD Repositories.
     *
     * @param d DTO object that contain updatable information (is not null).
     *          Null values of this object fields will be changed on {@code E} values.
     * @param e {@link AbstractEntity} subclass object that was obtained from database
     *          (or equal to object that is storing in the DataBase).
     * @return merged {@link AbstractEntity} subclass object.
     */
    public abstract E mapUpdate(D d, E e) throws MappingException;
}
