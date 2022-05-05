package com.cruznobre.rest.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Product")
public class ProductDTO {
    private Long id;
    private String name;
    @SchemaProperty(name = "Brand")
    private BrandDTO brand;
    private String category;
    private BigDecimal price;
}
