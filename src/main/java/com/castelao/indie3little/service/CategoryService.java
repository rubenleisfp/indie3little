package com.castelao.indie3little.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.castelao.indie3little.entities.Category;
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
	 * Crea una nueva categoria. Si ya existe otra con el mismo nombre lanza una excepcion DataIntegrityViolationException
	 * 
	 * Devuelve la categoria recien creada
	 * 
	 * @param category
	 * @return
	 * @throws DataIntegrityViolationException
	 */
	public Category create(Category category) throws DataIntegrityViolationException {
		if (categoryRepository.existByName(category.getName())) {
			LOG.error("Categoria con nombre ya existente: " + category.getName());
			throw new DataIntegrityViolationException("Categoria con nombre ya existente: " + category.getName());
		}
		return categoryRepository.save(category);
	}

	/**
	 * Si el id dde la categoria recibido existe, actualiza la mismo con los campos recibidos en categoryDetails
	 * Devuelve la categoria actualizada
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id de la categoria a buscar
	 * @param categoryDetails objeto con todos los campos a sobreescribir en la entidad
	 * @return
	 */
	public Optional<Category> update(Long id, Category categoryDetails) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto category a la entidad
			modelMapper.map(categoryDetails, category);

			return Optional.of(categoryRepository.save(category));
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
	public List<Category> search(String searchWord) {
		return categoryRepository.findByName(searchWord);
	}

}
