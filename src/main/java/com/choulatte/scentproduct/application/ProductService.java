package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getUserProductsList(Long userId);
    Optional<ProductDTO> getUserProductDetail(Long productId);

    List<ProductDTO> getProductsList();
    Optional<ProductDTO> getProductDetail(Long productId);

    List<ProductDTO> getBrandProducts(Long brandId);
    List<ProductDTO> getStatusProducts(StatusType status);

    void setProduct(ProductDTO productDTO, Long userIdx, String username);
    void setProduct(ProductDTO productDTO, Long userIdx, String username, Long productId);

    void deleteProduct(Long productId);
}
