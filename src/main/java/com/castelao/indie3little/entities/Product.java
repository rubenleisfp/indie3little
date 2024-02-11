package com.castelao.indie3little.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "brand")
    private String brand;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;
	
    

    
    
}
