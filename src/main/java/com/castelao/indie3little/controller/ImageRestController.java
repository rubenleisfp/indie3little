package com.castelao.indie3little.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.indie3little.dto.ImageAsBase64Dto;
import com.castelao.indie3little.service.ImageService;
import com.castelao.indie3little.utils.ImageUtils;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/images")
public class ImageRestController {

	ImageUtils imageUtils = new ImageUtils();

	@Autowired
	private ImageService imageService;



	@Operation(summary = "Upload an image to cloud")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Image created", content = {
	                @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)) }),
	        @ApiResponse(responseCode = "400", description = "Data not valid", content = @Content) })
	@PostMapping("/upload")
	public ResponseEntity<Map<String, String>> upload(@Valid @RequestBody ImageAsBase64Dto imageAsBase64) {
	    String url = null;
	    try {
	        url = imageService.uploadCloud(imageAsBase64.getBase64Data());
	    } catch (IOException ex) {
	        throw new RuntimeException("Error guardando la imagen en servidor de ficheros", ex);
	    }

	    Map<String, String> response = new HashMap<>();
	    response.put("url", url);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Delete a image by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Image deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Image not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteImage(@PathVariable("id") Long imageId) {
		boolean deleted = imageService.delete(imageId);
		if (deleted) {
			return ResponseEntity.ok().build();
		} else {
			return responseNotFound(imageId);
		}
	}

	private ResponseEntity<?> responseNotFound(Long productId) {
		String errorMessage = "Image with id '" + productId + "' not found";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorMessage));
	}

	
}
