package com.castelao.indie3little.mapper;

import java.util.ArrayList;
import java.util.List;

import com.castelao.indie3little.dto.ImageDto;
import com.castelao.indie3little.entities.Image;

public class ImageMapper {

    public static List<ImageDto> toDto(List<Image> entities) {
        List<ImageDto> dtos = new ArrayList<>();
        for (Image entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static ImageDto toDto(Image image) {
        ImageDto dto = new ImageDto();
        dto.setId(image.getImageId());
        dto.setUrl(image.getUrl());
        dto.setThumbnail(image.isThumbnail());
        return dto;
    }

    public static Image toEntity(ImageDto dto) {
        Image image = new Image();
        image.setThumbnail(dto.isThumbnail());
        image.setUrl(dto.getUrl());
        image.setImageId(dto.getId());
        return image;
    }
}