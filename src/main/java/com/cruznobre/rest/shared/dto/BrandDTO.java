package com.cruznobre.rest.shared.dto;

import com.cruznobre.rest.shared.util.LinkBag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="Brand")
public class BrandDTO {
    private Long id;
    private String name;
    List<LinkBag> links = new ArrayList<>();
}
