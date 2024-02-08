package com.castelao.indie3little.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class ImageUtils {

    // Método para convertir una imagen a base64
    public  String imageToBase64(File imageFile) {
        try {
            byte[] imageData = Files.readAllBytes(imageFile.toPath());
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para convertir una cadena base64 a una imagen y devolver un File
    public  File base64ToImage(String base64Data, String outputPath) {
        try {
            byte[] imageData = Base64.getDecoder().decode(base64Data);
            Path outputPathPath = Paths.get(outputPath);
            Files.write(outputPathPath, imageData, StandardOpenOption.CREATE);
            return outputPathPath.toFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}