package com.castelao.indie3little.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelao.indie3little.entities.Image;

/*
 * Clase encargada de recuperar la información de BBDD
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
