package com.castelao.indie3little.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.castelao.indie3little.dto.ProductDto;
import com.castelao.indie3little.entities.Category;
import com.castelao.indie3little.entities.Product;
import com.castelao.indie3little.mapper.ProductMapper;
import com.castelao.indie3little.repository.ProductRepository;
import com.castelao.indie3little.service.exceptions.NotFoundException;
import com.castelao.indie3little.service.exceptions.UploadException;

@Service
public class ProductService {

	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;


	private ModelMapper modelMapper = new ModelMapper();

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	/**
	 * Crea un producto nuevo asociado a la categoria recibida como argumento
	 * 
	 * Después crear una imagen con la URL del thumbnail recibido como argumento y
	 * la asocia al producto
	 * 
	 * Devuelve el productoDto recien creado
	 * 
	 * @param categoryId
	 * @param newProductDto
	 * @return
	 * @throws NotFoundException
	 * @throws UploadException
	 */
	@Transactional
	public ProductDto create(Long categoryId, ProductDto newProductDto)
			throws NotFoundException, UploadException {
		Optional<Category> category = categoryService.getById(categoryId);
		if (category.isEmpty()) {
			LOG.error("No existe la categoria con id: " + categoryId);
			LOG.error("ProductCreationDto " + newProductDto);
			throw new NotFoundException("Category with id " + categoryId + " not found");
		} else {

			Product product = ProductMapper.toEntity(newProductDto);
			product.setCategory(category.get());
			productRepository.save(product);
			
			return newProductDto;
		}
	}

	/**
	 * Si el id de producto recibido existe, actualiza el mismo con los campos
	 * recibidos en productDetails Devuelve el product actualizado
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id             del producto a buscar
	 * @param productDetails objeto con todos los campos a sobreescribir en la
	 *                       entidad
	 * @return
	 */
	public Optional<ProductDto> update(Long id, ProductDto productDetails) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);

			// Copiar propiedades desde tuObjetoDTO a entidadDB
			modelMapper.map(productDetails, product);
			Product productSaved = productRepository.save(product);

			return Optional.of(ProductMapper.toDto(productSaved));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Si existe un producto con el id recibido como argumento lo borra y devuelve
	 * true Sino existe devuelve falso
	 * 
	 * @param id del producto a borrar
	 * @return
	 */
	public boolean delete(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			productRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Optional<Product> getById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product;
	}

	/**
	 * Busca productos de la categoria indicada como argumento o que contenga
	 * searchWord en el titulo o la descripcion Opcionales ambos parametros en la
	 * busqueda
	 * 
	 * @param categoryId
	 * @param searchWord
	 * @return
	 */
	public List<ProductDto> search(Long categoryId, String searchWord) {
		List<ProductDto> searchDto = new ArrayList<ProductDto>();
		List<Product> search = productRepository.search(categoryId, searchWord);
		if (search != null) {

			searchDto = ProductMapper.toDto(search);
		}
		return searchDto;
	}

	public List<ProductDto> findProductByCategoryId(Long categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		return ProductMapper.toDto(products);
	}

}
