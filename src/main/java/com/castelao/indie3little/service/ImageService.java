package com.castelao.indie3little.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.castelao.indie3little.service.exceptions.UploadException;
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

	public List<Image> findAll() {
		return imageRepository.findAll();
	}

	public Image create(ImageDto imageDto, Product product) {
		Image image = ImageMapper.toEntity(imageDto);
		image.setProduct(product);
		Image imageSaved = imageRepository.save(image);
		return imageSaved;
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
	public String uploadCloud(String base64Data) throws IOException, UploadException {
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
		return ImageMapper.toDtoList(images);
	}

	public Optional<Image> getById(Long id) {
		return imageRepository.findById(id);
	}

}
