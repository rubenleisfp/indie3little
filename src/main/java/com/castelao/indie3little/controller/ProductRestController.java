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

import com.castelao.indie3little.dto.ImageDto;
import com.castelao.indie3little.dto.ProductCreationDto;
import com.castelao.indie3little.dto.ProductDto;
import com.castelao.indie3little.entities.Product;
import com.castelao.indie3little.mapper.ProductMapper;
import com.castelao.indie3little.service.ImageService;
import com.castelao.indie3little.service.ProductService;
import com.castelao.indie3little.service.exceptions.UploadException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * 
 * 
 */

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ImageService imageService;

	@Operation(summary = "Get all products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }) })
	
	public List<ProductDto> findAll() {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Get an product by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	
	public ResponseEntity<?> getById(@PathVariable("id") Long productId) {

		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Get all products with %search% or categoryID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })

	public List<ProductDto> search(@RequestParam(name = "categoryId", required = false) Long categoryId,
			@RequestParam(name = "searchWord", required = false) String searchWord) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Delete a product by  id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public ResponseEntity<?> delete(@PathVariable("id") Long categoryId) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Update a product by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public ResponseEntity<?> update(@PathVariable(value = "id") Long productId,
			@Valid @RequestBody ProductDto productDto) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	@Operation(summary = "Attach an image URL to a product")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Image attached", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductCreationDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public ResponseEntity<?> attachImage(@PathVariable("productId") Long productId,
			@Valid @RequestBody ImageDto imageDto) throws UploadException {

		throw new UnsupportedOperationException("Falta por implementar");

	}

	private ResponseEntity<?> responseNotFound(Long productId) {
		String errorMessage = "Product with id '" + productId + "' not found";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorMessage));
	}
}
