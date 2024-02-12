package com.castelao.indie3little.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.castelao.indie3little.entities.Product;
import com.castelao.indie3little.repository.ProductRepositoryCustom;

import jakarta.persistence.EntityManager;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@Autowired
	EntityManager em;

	public List<Product> search(Long category, String searchWord) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

}
