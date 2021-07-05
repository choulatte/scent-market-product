package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getUserProductsList(String userIdx);
    ProductDTO getUserProductDetail(String productIdx);

    List<ProductDTO> getProductsList();
    ProductDTO getProductDetail(String productIdx);

    void setProduct(String userIdx, String username, ProductDTO productDTO);
    void deleteProduct(String productIdx);
}
