package com.castelao.indie3little.repository;

import java.util.List;

import com.castelao.indie3little.entities.Product;

public interface ProductRepositoryCustom {
	 public List<Product> search(Long category, String searchWord);
}
