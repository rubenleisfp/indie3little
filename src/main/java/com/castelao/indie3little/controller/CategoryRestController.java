package com.castelao.indie3little.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.indie3little.dto.CategoryDto;
import com.castelao.indie3little.entities.Category;
import com.castelao.indie3little.mapper.CategoryMapper;
import com.castelao.indie3little.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


/**
 * http://localhost:8080/v3/api-docs
 * http://localhost:8080/swagger-ui/index.html
 * 
 */

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@Operation(summary = "Get all categories")
	@GetMapping
	public List<CategoryDto> findAll() {
		List<Category> categories = categoryService.findAll();
		return CategoryMapper.toDto(categories);
	}
	
	@Operation(summary = "Get an category by its id")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Category found", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
	    }),
		@ApiResponse(responseCode = "404", description = "Category not found", content = {
		        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
	})		
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long categoryId) {

		Optional<Category> category = categoryService.getById(categoryId);
		if (category.isPresent()) {
			CategoryDto categoryDto = CategoryMapper.toDto(category.get());
			return ResponseEntity.ok().body(categoryDto);
		} else {
			return responseNotFound(categoryId);
		}
	}

	@Operation(summary = "Create a category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Category created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
			        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
			})
	@PostMapping
	public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
		Category category = CategoryMapper.toEntity(categoryDto);
		category = categoryService.create(category);
		CategoryDto dtoWithId = CategoryMapper.toDto(category);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
	}

	@Operation(summary = "Update an category by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Category updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Category not found", content = {
			        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
			        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
	})
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long categoryId,
			@Valid @RequestBody CategoryDto categoryDto) {
		Category category = CategoryMapper.toEntity(categoryDto);
		Optional<Category> optionalCategory = categoryService.update(categoryId, category);
		if (optionalCategory.isPresent()) {
			categoryDto = CategoryMapper.toDto(optionalCategory.get());
			return ResponseEntity.ok(categoryDto);
		} else {
			return responseNotFound(categoryId);
		}
	}

	@Operation(summary = "Delete a category by its id")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Category deleted", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))
	    }),
	    @ApiResponse(responseCode = "404", description = "Category not found", content = {
	        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
	    })
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long categoryId) {
		boolean deleted = categoryService.delete(categoryId);
		if (deleted) {
			return ResponseEntity.ok().build();
		} else {
			return responseNotFound(categoryId);
		}
	}
	
	private ResponseEntity<?> responseNotFound(Long categoryId) {
		String errorMessage = "Category with id '" +  categoryId + "' not found";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorMessage));
	}
}
