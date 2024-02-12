package com.castelao.indie3little.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {

	private Long productId;

	private String title;

	private String description;

	private BigDecimal price;

	private BigDecimal rating;

	private Integer stock;

	private String brand;

	private Category category;

	private List<Image> images;

}
