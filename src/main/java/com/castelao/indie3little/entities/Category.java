package com.castelao.indie3little.entities;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Category {
	
	private Long categoryId;

	private String name;

    private List<Product> products;
	
    public Category(String name ) {
        super();
        this.name = name;
    }






}
