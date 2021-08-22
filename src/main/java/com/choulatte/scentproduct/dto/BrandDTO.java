package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Brand;
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

    private Long brandIdx;
    private String brandName;
    private List<ProductDTO> products;

    public Brand toEntity() {
        return Brand.builder().brandIdx(this.getBrandIdx())
                .brandName(this.brandName).build();
    }
}
