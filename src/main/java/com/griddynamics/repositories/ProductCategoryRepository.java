package com.griddynamics.repositories;

import com.griddynamics.entities.Product;
import com.griddynamics.entities.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {

    List<ProductCategory> findByProductId(Integer id);

    List<ProductCategory> findByProduct(Product product);
}
