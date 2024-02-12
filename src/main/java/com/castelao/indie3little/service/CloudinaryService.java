package com.castelao.indie3little.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class CloudinaryService {

	private static Logger LOG = LoggerFactory.getLogger(CloudinaryService.class);
	private static final String URL = "url";
	
	private Cloudinary cloudinary;

	public CloudinaryService() {
		Dotenv dotenv = Dotenv.load();
		cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
		cloudinary.config.secure = true;
		System.out.println(cloudinary.config.cloudName);
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
	public String upload(String base64Data) throws IOException {
		throw new UnsupportedOperationException("Falta por implementar");
	}
}
