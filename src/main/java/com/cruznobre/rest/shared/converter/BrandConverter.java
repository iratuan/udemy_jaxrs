package com.cruznobre.rest.shared.converter;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.v1.dto.BrandDTO;

public class BrandConverter {

    public static Brand converter(BrandDTO dto){
        Brand entity = new Brand();
        
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }

    public static BrandDTO converter(Brand entity){
        BrandDTO dto = new BrandDTO();
        
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }
    
}
