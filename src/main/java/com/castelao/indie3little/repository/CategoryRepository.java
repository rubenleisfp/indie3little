package com.castelao.indie3little.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.castelao.indie3little.entities.Category;

/*
 * 
 * TODO Faltan los @Query
 * 
 * Clase encargada de recuperar la información de BBDD
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	List<Category> findByName(@Param("name") String name);
	

	boolean existByName(@Param("name") String name);
	
}
