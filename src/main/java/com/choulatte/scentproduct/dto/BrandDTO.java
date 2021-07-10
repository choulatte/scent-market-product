package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BrandDTO {

    private Long brandId;
    private String brandName;
    private List<ProductDTO> products;

    public Brand toEntity() {
        return Brand.builder().brandId(this.getBrandId())
                .brandName(this.brandName).build();
    }
}
