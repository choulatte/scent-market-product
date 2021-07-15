package com.choulatte.scentproduct.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Builder
public class ProductPageDTO {

    private int totalPage;
    private int currentPage;
    private List<ProductDTO> products = new ArrayList<>();

    public ProductPageDTO(int totalPage, int currentPage, List<ProductDTO> products) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.products = products;
    }
}
