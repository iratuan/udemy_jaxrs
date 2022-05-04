package com.cruznobre.rest.shared.converter;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.shared.dto.BrandDTO;
import jakarta.ejb.Stateless;
import org.modelmapper.ModelMapper;


@Stateless
public class BrandConverter{

    public static BrandDTO toDTO(Brand entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, BrandDTO.class);
    }

    public static Brand toEntity(BrandDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Brand.class);
    }
}