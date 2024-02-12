package com.castelao.indie3little.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.castelao.indie3little.entities.Image;

/*
 * TODO Faltan los @Query
 * 
 * Clase encargada de recuperar la información de BBDD
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

	List<Image> findByProductId(Long productId);

}
