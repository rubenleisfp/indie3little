package com.castelao.indie3little.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.castelao.indie3little.entities.Product;

/*
 * Clase encargada de recuperar la información de BBDD
 */
public interface ProductRepository extends JpaRepository<Product, Long>,  ProductRepositoryCustom {
	@Query ("select p from Product p where p.category.categoryId = :categoryId")
	List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

}
