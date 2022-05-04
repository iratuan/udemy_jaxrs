package com.cruznobre.rest.shared.dto;

import com.cruznobre.rest.core.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="Product")
public class ProductDTO {
    private Integer id;
    private Brand brand;
    private String category;
    private BigDecimal price;
}
