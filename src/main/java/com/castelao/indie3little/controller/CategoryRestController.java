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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.indie3little.dto.CategoryDto;
import com.castelao.indie3little.dto.ProductCreationDto;
import com.castelao.indie3little.dto.ProductDto;
import com.castelao.indie3little.entities.Category;
import com.castelao.indie3little.mapper.CategoryMapper;
import com.castelao.indie3little.service.CategoryService;
import com.castelao.indie3little.service.ProductService;
import com.castelao.indie3little.service.exceptions.NotFoundException;
import com.castelao.indie3little.service.exceptions.UploadException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * 
 */

@RestController

public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;


	@Operation(summary = "Get all categories")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categories found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)) }) })
	@GetMapping
	public List<CategoryDto> findAll() {
		List<Category> categories = categoryService.findAll();
		return CategoryMapper.toDto(categories);
	}

	@Operation(summary = "Get an category by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Category found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Category not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })


	public ResponseEntity<?> getById(@PathVariable("categoryId") Long categoryId) {

		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Get products from a category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved category products", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
			@ApiResponse(responseCode = "404", description = "Category not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })


	public ResponseEntity<?> getCategoryProducts(
			@Parameter(description = "ID of the category to retrieve products from", required = true) @PathVariable("categoryId") Long categoryId) {

		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Create a category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Category created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Update an category by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Category updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Category not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public ResponseEntity<?> update(@PathVariable(value = "id") Long categoryId,
			@Valid @RequestBody CategoryDto categoryDto) {

		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Delete a category by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Category deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Category not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public ResponseEntity<?> delete(@PathVariable("id") Long categoryId) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Create a product and the thumbnail image")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Product created", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductCreationDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Category not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public ResponseEntity<?> createProduct(@PathVariable("categoryId") Long categoryId,
			@Valid @RequestBody ProductCreationDto productCreationDto) throws UploadException {
		throw new UnsupportedOperationException("Falta por implementar");

	}

	@Operation(summary = "Get all category with %name%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categories found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))) }) })

	public List<CategoryDto> search(@RequestParam(name = "name") String name) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	private ResponseEntity<?> responseNotFound(Long categoryId) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	private ResponseEntity<?> responseNotFound(String message) {
		throw new UnsupportedOperationException("Falta por implementar");
	}
}
