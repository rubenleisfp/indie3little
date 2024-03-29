package com.castelao.indie3little.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
	
	
	private Long id;
	
	private String url;
	
	private boolean isThumbnail;


}
