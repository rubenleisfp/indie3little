package com.castelao.indie3little.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.castelao.indie3little.dto.ProductDto;
import com.castelao.indie3little.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castelao.indie3little.dto.ImageDto;
import com.castelao.indie3little.entities.Image;
import com.castelao.indie3little.entities.Product;
import com.castelao.indie3little.mapper.ImageMapper;
import com.castelao.indie3little.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	CloudinaryService cloudinaryService;

	public List<ImageDto> findAll() {
		return ImageMapper.toDto(imageRepository.findAll());
	}

	public ImageDto create(ImageDto imageDto, ProductDto productDto) {
		Image image = ImageMapper.toEntity(imageDto);
		Product product = ProductMapper.toEntity(productDto);
		image.setProduct(product);
		Image imageSaved = imageRepository.save(image);
		return ImageMapper.toDto(imageSaved);
	}

	/**
	 * Sube la imagen recibida como argumento en base64 al cloud
	 * 
	 * Devuelve la URL publica donde esta alojada
	 * 
	 * @param base64Data
	 * @return
	 * @throws IOException
	 */
	public String uploadCloud(String base64Data) throws IOException {
		String url = cloudinaryService.upload(base64Data);
		return url;
	}

	/**
	 * Si existe una imagen con el id recibido como argumento la borra y devuelve
	 * true Sino existe devuelve falso
	 * 
	 * @param id de la imagen a borrar
	 * @return
	 */
	public boolean delete(Long id) {
		Optional<Image> optionalImage = imageRepository.findById(id);
		if (optionalImage.isPresent()) {
			imageRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Busca todas las imagenes asociadas a un producto.
	 * 
	 * @param productId
	 * @return
	 */
	public List<ImageDto> findAllByProductId(Long productId) {
		List<Image> images = imageRepository.findByProductId(productId);
		return ImageMapper.toDto(images);
	}

	/**
	 * Busca una imagen por ID
	 * @param id
	 *
	 * @return
	 */
	public Optional<ImageDto> getById(Long id) {
		Optional<Image> image = imageRepository.findById(id);
		if (image.isPresent()) {
			return Optional.of(ImageMapper.toDto(image.get()));
		} else {
			return Optional.empty();
		}
	}

}
