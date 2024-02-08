package com.castelao.indie3little.mapper;

import com.castelao.indie3little.dto.CategoryDto;
import com.castelao.indie3little.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public static List<CategoryDto> toDto(List<Category> entities) {
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static CategoryDto toDto(Category entity) {
        CategoryDto dto = new CategoryDto(entity.getCategoryId(),entity.getName());
        return dto;
    }

    public static Category toEntity(CategoryDto dto) {
        Category entity = new Category(dto.getName());
        return entity;
    }
}