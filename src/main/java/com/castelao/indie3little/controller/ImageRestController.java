package com.castelao.indie3little.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.indie3little.dto.ImageDto;
import com.castelao.indie3little.entities.Category;
import com.castelao.indie3little.service.ImageService;
import com.castelao.indie3little.utils.ImageUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * http://localhost:8080/v3/api-docs http://localhost:8080/swagger-ui/index.html
 * 
 */

@RestController
@RequestMapping("/api/images")
public class ImageRestController {

	ImageUtils imageUtils = new ImageUtils();

	@Autowired
	private ImageService imageService;

	/*
	 * @Operation(summary = "Get all categories")
	 * 
	 * @GetMapping public List<CategoryDto> findAll() { List<Category> categories =
	 * imageService.findAll(); return CategoryMapper.toDto(categories); }
	 * 
	 * @Operation(summary = "Get an category by its id")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(responseCode = "200", description = "Category found", content =
	 * { @Content(mediaType = "application/json", schema = @Schema(implementation =
	 * Category.class)) }),
	 * 
	 * @ApiResponse(responseCode = "404", description = "Category not found",
	 * content = @Content) })
	 * 
	 * @GetMapping(value = "/{id}") public ResponseEntity<CategoryDto>
	 * getById(@PathVariable("id") Long employeeId) {
	 * 
	 * Optional<Category> employee = imageService.getById(employeeId); if
	 * (employee.isPresent()) { CategoryDto employeeDto =
	 * CategoryMapper.toDto(employee.get()); return
	 * ResponseEntity.ok().body(employeeDto); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */

	@Operation(summary = "Create an image")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Image created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = @Content) })
	@PostMapping
	public ResponseEntity<ImageDto> create(@Valid @RequestBody ImageDto imageDto) {

		imageUtils.base64ToImage(imageDto.getBase64Data(), "src/test/resources/1.jpg");
		try {
			imageService.create(imageDto);
		} catch (IOException ex) {
			throw new RuntimeException("Error guardando la imagen en servidor de ficheros", ex);
		}
//		Category employee = CategoryMapper.toEntity(imageDto);
//		employee = imageService.create(employee);
//		CategoryDto dtoWithId = CategoryMapper.toDto(employee);
//		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);

		return new ResponseEntity<>(imageDto, HttpStatus.CREATED);
	}

	/*
	 * @Operation(summary = "Update an category by its id")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(responseCode = "200", description = "Category updated", content
	 * = { @Content(mediaType = "application/json", schema = @Schema(implementation
	 * = CategoryDto.class)) }),
	 * 
	 * @ApiResponse(responseCode = "404", description = "Category not found",
	 * content = @Content),
	 * 
	 * @ApiResponse(responseCode = "400", description = "Data not valid", content
	 * = @Content) })
	 * 
	 * @PutMapping("/{id}") public ResponseEntity<CategoryDto>
	 * update(@PathVariable(value = "id") Long employeeId,
	 * 
	 * @Valid @RequestBody CategoryDto employeeDto) { Category employee =
	 * CategoryMapper.toEntity(employeeDto); Optional<Category> optionalEmployee =
	 * imageService.update(employeeId, employee); if (optionalEmployee.isPresent())
	 * { employeeDto = CategoryMapper.toDto(optionalEmployee.get()); return
	 * ResponseEntity.ok(employeeDto); } else { return
	 * ResponseEntity.notFound().build(); } }
	 * 
	 * @Operation(summary = "Delete an category by its id")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(responseCode = "200", description = "Category deleted", content
	 * = { @Content(mediaType = "application/json")}),
	 * 
	 * @ApiResponse(responseCode = "404", description = "Category not found",
	 * content = @Content) })
	 * 
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<CategoryDto>
	 * delete(@PathVariable("id") Long employeeId) { boolean deleted =
	 * imageService.delete(employeeId); if (deleted) { return
	 * ResponseEntity.ok().build(); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */
}
