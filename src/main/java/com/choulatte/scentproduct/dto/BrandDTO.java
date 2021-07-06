package com.choulatte.scentproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BrandDTO {

    private Long brandId;
    private String brandName;
    private List<ProductDTO> products;
}
