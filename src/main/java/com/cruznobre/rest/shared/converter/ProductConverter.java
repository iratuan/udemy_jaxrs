package com.cruznobre.rest.shared.converter;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.core.entity.Product;
import com.cruznobre.rest.shared.dto.BrandDTO;
import com.cruznobre.rest.shared.dto.ProductDTO;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;

@Stateless
public class ProductConverter {

    public static ProductDTO toDTO(Product entity) {
        if(entity == null ){
            throw new NotFoundException("Registro não encontrado");
        }
        ModelMapper modelMapper = new ModelMapper();
        BrandDTO brandDTO = BrandConverter.toDTO(entity.getBrand());
        entity.setBrand(null);
        ProductDTO dto = modelMapper.map(entity, ProductDTO.class);
        dto.setBrand(brandDTO);
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
       if(dto == null){
           throw new NotFoundException("Registro não encontrado");
       }
        ModelMapper modelMapper = new ModelMapper();
        Brand brandEntity = BrandConverter.toEntity(dto.getBrand());
        dto.setBrand(null);
        Product productEntity = modelMapper.map(dto, Product.class);
        productEntity.setBrand(brandEntity);
        return productEntity;
    }
}