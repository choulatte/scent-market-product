package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getUserProductsList(Long userId);
    ProductDTO getUserProductDetail(Long productId);
    List<ProductDTO> getUserInterestingProductList(Long userId);

    List<ProductDTO> getProductsList();
    ProductDTO getProductDetail(Long productId);

    List<ProductDTO> getBrandProducts(Long brandId);
    List<ProductDTO> getStatusProducts(String status);

    void setProduct(ProductDTO productDTO, Long userIdx, String username);
    void setProduct(ProductDTO productDTO, Long userIdx, String username, Long productId);

    void deleteProduct(Long productId);
}
