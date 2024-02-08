package com.castelao.indie3little.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelao.indie3little.entities.Product;

/*
 * Clase encargada de recuperar la información de BBDD
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
