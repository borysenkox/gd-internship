package com.griddynamics.repositories;

import com.griddynamics.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
