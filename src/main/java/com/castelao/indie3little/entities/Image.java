package com.castelao.indie3little.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity

public class Image {

	private Long imageId;

	private String url;

	private boolean isThumbnail;

	private Product product;

}