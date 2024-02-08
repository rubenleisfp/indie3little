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

	public Category create(Category category) throws DataIntegrityViolationException {
		if (categoryRepository.existByName(category.getName())) {
			LOG.error("Categoria con nombre ya existente: " + category.getName());
			throw new DataIntegrityViolationException("Categoria con nombre ya existente: " + category.getName());
		}
		return categoryRepository.save(category);
	}

	public Optional<Category> update(Long id, Category employeeDetails) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);

			// Copiar propiedades desde tuObjetoDTO a entidadDB
			modelMapper.map(employeeDetails, category);

			return Optional.of(categoryRepository.save(category));
		} else {
			return Optional.empty();
		}
	}

	public boolean delete(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
			categoryRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Optional<Category> getById(Long id) {
		return categoryRepository.findById(id);
	}

}
