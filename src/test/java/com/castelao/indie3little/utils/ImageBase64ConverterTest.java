package com.castelao.indie3little.utils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageBase64ConverterTest {

	private static Logger LOG = LoggerFactory.getLogger(ImageService.class);
	
    @Test
    public void testImageToBase64AndBase64ToImage() {
    	ImageUtils imageUtils = new ImageUtils();
    	
    	
        // Ruta de la imagen de prueba
        String imagePath = "src/test/resources/images/thumbnail.jpg";
        File imageFile = new File(imagePath);

        // Convertir la imagen a base64
        String base64String = imageUtils.imageToBase64(imageFile);

        LOG.error("base64String:" + base64String);
        
        // Verificar que la cadena base64 no sea nula
        assertNotNull(base64String);

        // Convertir la cadena base64 a imagen y guardarla
        String outputPath = "src/test/resources/images/decoded_thumbnail.jpg";
        File decodedImageFile = imageUtils.base64ToImage(base64String, outputPath);

        // Verificar que el archivo decodificado no sea nulo
        assertNotNull(decodedImageFile);

        // Verificar que el archivo decodificado exista
        assertEquals(true, decodedImageFile.exists());
        
        // Eliminar el archivo decodificado después de la prueba
        decodedImageFile.delete();
    }
}