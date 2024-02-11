package com.castelao.indie3little.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.castelao.indie3little.entities.Product;
import com.castelao.indie3little.repository.ProductRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@Autowired
	EntityManager em;

	public List<Product> search(Long category, String searchWord) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> root = cq.from(Product.class);

		List<Predicate> predicates = new ArrayList<>();

		if (category != null) {
			predicates.add(cb.equal(root.get("category").get("categoryId"), category));
		}

		if (searchWord != null && !searchWord.isEmpty()) {
			Predicate titlePredicate = cb.like(cb.lower(root.get("title")), "%" + searchWord.toLowerCase() + "%");
			Predicate descriptionPredicate = cb.like(cb.lower(root.get("description")),
					"%" + searchWord.toLowerCase() + "%");
			predicates.add(cb.or(titlePredicate, descriptionPredicate));
		}

		cq.where(predicates.toArray(new Predicate[0]));

		return em.createQuery(cq).getResultList();
	}

}
