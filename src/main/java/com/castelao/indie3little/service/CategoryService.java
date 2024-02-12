package com.castelao.indie3little.service;

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
import com.castelao.indie3little.repository.CategoryRepository;

@Service
public class CategoryService {

	private static Logger LOG = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<Category> findAll() {
		throw new UnsupportedOperationException("Falta por implementar");
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
		throw new UnsupportedOperationException("Falta por implementar");
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
		throw new UnsupportedOperationException("Falta por implementar");
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
		throw new UnsupportedOperationException("Falta por implementar");
	}

	public Optional<Category> getById(Long id) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	/**
	 * Busca categorias que contengan la palabra recibida como argumento
	 * 
	 * @param searchWord
	 * @return
	 */
	public List<CategoryDto> search(String searchWord) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

}
