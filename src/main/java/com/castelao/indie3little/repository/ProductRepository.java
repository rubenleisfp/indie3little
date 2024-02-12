package com.castelao.indie3little.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.castelao.indie3little.entities.Product;

/*
 * TODO Faltan los @Query
 * 
 * Clase encargada de recuperar la información de BBDD
 */
public interface ProductRepository extends JpaRepository<Product, Long>,  ProductRepositoryCustom {

	List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

}
