package com.castelao.indie3little.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.castelao.indie3little.dto.CategoryDto;
import com.castelao.indie3little.entities.Category;
import com.castelao.indie3little.mapper.CategoryMapper;
import com.castelao.indie3little.repository.CategoryRepository;

@Service
public class CategoryService {

	private static Logger LOG = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	/**
	 * Crea una nueva categoria. Si ya existe otra con el mismo nombre lanza una
	 * excepcion DataIntegrityViolationException
	 * 
	 * Devuelve la categoriaDto recien creada
	 * 
	 * @param categoryDto
	 * @return
	 * @throws DataIntegrityViolationException
	 */
	public CategoryDto create(CategoryDto categoryDto) throws DataIntegrityViolationException {
		if (categoryRepository.existByName(categoryDto.getName())) {
			LOG.error("Categoria con nombre ya existente: " + categoryDto.getName());
			throw new DataIntegrityViolationException("Categoria con nombre ya existente: " + categoryDto.getName());
		}
		Category category = CategoryMapper.toEntity(categoryDto);
		category = categoryRepository.save(category);
		CategoryDto dtoCreated = CategoryMapper.toDto(category);
		return dtoCreated;
	}

	/**
	 * Si el id de la categoria recibido existe, actualiza la mismo con los campos
	 * recibidos en categoryDto
	 * 
	 * Devuelve la categoria actualizada
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id              de la categoria a buscar
	 * @param categoryDetails objeto con todos los campos a sobreescribir en la
	 *                        entidad
	 * @return
	 */
	public Optional<CategoryDto> update(Long id, CategoryDto categoryDto) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto category a la entidad
			modelMapper.map(categoryDto, category);

			Category categorySaved = categoryRepository.save(category);
			return Optional.of(CategoryMapper.toDto(categorySaved));
		} else {
			LOG.info("categoria no encontrada: " + id);
			return Optional.empty();
		}
	}

	/**
	 * Si existe la categoria con el id recibido como argumento lo borra y devuelve
	 * true
	 * 
	 * Sino existe devuelve falso
	 * 
	 * @param id de la categoria a borrar
	 * @return
	 */
	public boolean delete(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
			categoryRepository.deleteById(id);
			return true;
		} else {
			LOG.info("categoria no encontrada: " + id);
			return false;
		}
	}

	public Optional<Category> getById(Long id) {
		return categoryRepository.findById(id);
	}

	/**
	 * Busca categorias que contengan la palabra recibida como argumento
	 * 
	 * @param searchWord
	 * @return
	 */
	public List<CategoryDto> search(String searchWord) {
		List<CategoryDto> dtos = new ArrayList<CategoryDto>();
		List<Category> categories = categoryRepository.findByName(searchWord);

		if (categories != null) {
			dtos = CategoryMapper.toDto(categories);
		}

		return dtos;
	}

}
