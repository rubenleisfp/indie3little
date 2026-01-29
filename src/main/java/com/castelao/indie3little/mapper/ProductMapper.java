package com.castelao.indie3little.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.castelao.indie3little.dto.ProductDto;
import com.castelao.indie3little.entities.Product;
import com.castelao.indie3little.service.ProductService;

public class ProductMapper {
	
	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
    public static List<ProductDto> toDto(List<Product> entities) {
        List<ProductDto> dtos = new ArrayList<>();
        for (Product entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
	
    
    public static ProductDto toDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setProductId(entity.getProductId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setRating(entity.getRating());
        dto.setStock(entity.getStock());
        dto.setBrand(entity.getBrand());

        // Mapear Category
        dto.setCategoryDto(CategoryMapper.toDto(entity.getCategory()));

        // Mapear List<Image>
        int imagesSize = 0;

      
        LOG.error("images size: " + imagesSize);

        return dto;
    }


    public static Product toEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setProductId(dto.getProductId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setRating(dto.getRating());
        entity.setStock(dto.getStock());
        entity.setBrand(dto.getBrand());

        // Mapear CategoryDto
        if (dto.getCategoryDto() != null) {
        	entity.setCategory(CategoryMapper.toEntity(dto.getCategoryDto()));
        }



        return entity;
    }
}
