package com.castelao.indie3little.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.castelao.indie3little.dto.ImageDto;
import com.castelao.indie3little.dto.ProductCreationDto;
import com.castelao.indie3little.dto.ProductDto;
import com.castelao.indie3little.entities.Product;
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

	@Autowired
	private ImageService imageService;

	private ModelMapper modelMapper = new ModelMapper();

	public List<Product> findAll() {
		throw new UnsupportedOperationException("Falta por implementar");
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
	 * @param productCreationDto
	 * @return
	 * @throws NotFoundException
	 * @throws UploadException
	 */
	@Transactional
	public ProductDto create(Long categoryId, ProductCreationDto productCreationDto)
			throws NotFoundException, UploadException {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	/**
	 * Comprueba si un productoDto tiene un thumbnail ya asociado
	 * 
	 * @param product
	 * @return
	 */
	public boolean hasThumnbail(Product product) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	/**
	 * Crea una imagen con la URL indicada asociado al producto recibido
	 * 
	 * @param url
	 * @param productDto
	 * @return
	 */
	private ImageDto createThumbnail(String url, Product product) {
		throw new UnsupportedOperationException("Falta por implementar");
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
		throw new UnsupportedOperationException("Falta por implementar");
	}

	/**
	 * Si existe un producto con el id recibido como argumento lo borra y devuelve
	 * true Sino existe devuelve falso
	 * 
	 * @param id del producto a borrar
	 * @return
	 */
	public boolean delete(Long id) {
		throw new UnsupportedOperationException("Falta por implementar");
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
		throw new UnsupportedOperationException("Falta por implementar");
	}

	public List<ProductDto> findProductByCategoryId(Long categoryId) {
		throw new UnsupportedOperationException("Falta por implementar");
	}

}
