package com.cruznobre.rest.shared.converter;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.v1.dto.BrandDTO;
import jakarta.ejb.Stateless;
import org.modelmapper.ModelMapper;

@Stateless
public class BrandConverter{
    
    public static BrandDTO apply(Brand entity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, BrandDTO.class);
    }

    public static Brand apply(BrandDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Brand.class);
    }
}
