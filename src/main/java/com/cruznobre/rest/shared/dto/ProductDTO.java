package com.cruznobre.rest.shared.dto;

import com.cruznobre.rest.shared.util.LinkBag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private List<LinkBag> links =new ArrayList<>();
}
