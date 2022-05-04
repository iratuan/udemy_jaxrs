package com.cruznobre.rest.shared.converter;

import com.cruznobre.rest.core.entity.Product;
import com.cruznobre.rest.shared.dto.ProductDTO;
import jakarta.ejb.Stateless;
import org.modelmapper.ModelMapper;

@Stateless
public class ProductConverter{

    public static ProductDTO toDTO(Product entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ProductDTO.class);
    }

    public static Product toEntity(ProductDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Product.class);
    }
}