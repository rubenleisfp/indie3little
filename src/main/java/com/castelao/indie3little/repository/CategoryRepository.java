package com.castelao.indie3little.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.castelao.indie3little.entities.Category;

/*
 * Clase encargada de recuperar la información de BBDD
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	@Query ("select c from Category c where c.name= :name")
	List<Category> findByName(@Param("name") String name);
	
	@Query ("select count(c)>0 from Category c where c.name= :name")
	boolean existByName(@Param("name") String name);
	
}
