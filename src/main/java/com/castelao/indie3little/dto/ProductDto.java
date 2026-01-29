package com.castelao.indie3little.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de la capa del controlador para modelar productos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private Long productId;

    @NotEmpty
	private String title;

	private String description;

    @DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal price;

	private BigDecimal rating;

    @Min(value = 0L, message = "The value must be positive")
	private Integer stock;

    @NotEmpty
	private String brand;

	private CategoryDto categoryDto;

}


