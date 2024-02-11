package com.castelao.indie3little.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO para nuestras peticiones al Controller
 * @author 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {


    private Long id;
	
    @NotEmpty
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}
