package com.griddynamics.mappers;

import com.griddynamics.entities.AbstractEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code EntityMapper} - abstract class to map DTO objects to the {@link com.griddynamics.entities.AbstractEntity}
 * subclasses and vice versa.
 * @param <E> class.
 * @param <D> DTO class.
 */
public abstract class EntityMapper<E extends AbstractEntity, D> {


    /**
     * Maps entity {@code E} to its DTO object {@code D}.
     * @param e database entity
     * @return DTO object of entity
     */
    public abstract D mapDTO(E e);

    /**
     * Maps DTO object of entity {@code D} to database entity {@code E}.
     * @param d DTO object
     * @return database entity
     */
    public abstract E mapEntity(D d);

    /**
     * Maps List of DTO objects {@code D} to List of Entity objects {@code E}.
     * To use this default implementation - {@link EntityMapper#mapEntity(D)} should also be implemented.
     * @param dList {@link List<D>} that used as source of {@code D} objects
     *      *                                          for mapping to {@code E} objects.
     * @return object of {@link List<E>} that based on {@code dList}.
     */
    public List<E> mapList(List<D> dList) {

        List<E> eList = new ArrayList<>();

        dList.forEach(d -> eList.add(mapEntity(d)));

        return eList;
    }

    /**
     * Maps List of Entity objects {@code E} to List of DTO objects {@code D}.
     * To use this default implementation - {@link EntityMapper#mapDTO(AbstractEntity)} should also be implemented.
     * @param eList {@link List<AbstractEntity>} that used as source of {@code E} objects
     *                                          for mapping to {@code D} objects.
     * @return object of {@link List<D>} that based on {@code eList}.
     */
    public List<D> mapDTOList(List<E> eList) {

        List<D> dList = new ArrayList<>();

        eList.forEach(e-> dList.add(mapDTO(e)));

        return dList;
    }

    /**
     * Maps {@link Iterable} from {@link CrudRepository#findAll()} to the {@link List} with {@code E} entities.
     * @param i {@link Iterable}
     * @return {@link List} with {@code E} entities
     */
    public abstract List<E> mapList(Iterable<E> i);

    /**
     * Maps {@link Iterable} from {@link CrudRepository#findAll()} to the {@link List} with {@code D} DTO objects.
     * @param i {@link Iterable}
     * @return {@link List} with {@code D} DTO objects
     */
    public abstract List<D> mapDTOList(Iterable<E> i);

    /**
     * Changes all null-values variables of {@code E} entity to values of {@code D} object.
     * Method mainly used for update operation in CRUD Repositories.
     * @param d DTO object that contain updatable information (is not null).
     *          Null values of this object fields will be changed on {@code E} values.
     * @param e {@link AbstractEntity} subclass object that was obtained from database
     *          (or equal to object that is storing in the DataBase).
     * @return merged {@link AbstractEntity} subclass object.
     */
    public abstract E mapUpdate(D d, E e);
}
