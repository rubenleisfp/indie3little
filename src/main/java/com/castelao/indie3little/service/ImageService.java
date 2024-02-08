package com.castelao.indie3little.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.castelao.indie3little.dto.ImageDto;
import com.castelao.indie3little.entities.Category;
import com.castelao.indie3little.entities.Image;
import com.castelao.indie3little.repository.ImageRepository;

import jakarta.validation.Valid;


@Service
public class ImageService {

	private static Logger LOG = LoggerFactory.getLogger(ImageService.class);

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	CloudinaryService cloudinaryService;


	public List<Image> findAll() {
		return imageRepository.findAll();
	}

	public Image create(ImageDto imageDto) throws IOException {
		cloudinaryService.upload(imageDto.getBase64Data());
		return null;
	}


	public Optional<Image> update(Long id, Category employeeDetails) {
		Optional<Image> optionalEmployee = imageRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Image image = optionalEmployee.get();

			return Optional.of(imageRepository.save(image));
		} else {
			return Optional.empty();
		}
	}

	public boolean delete(Long id) {
		Optional<Image> optionalImage = imageRepository.findById(id);
	    if (optionalImage.isPresent()) {
	    	imageRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
	}
	
	public Optional<Image> getById(Long id) {
		return imageRepository.findById(id);
	}

}
