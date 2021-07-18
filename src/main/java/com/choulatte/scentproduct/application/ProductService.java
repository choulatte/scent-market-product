package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductPageDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface ProductService {
    ProductPageDTO getUserProductPage(Long userId, Pageable pageable);
    ProductPageDTO getProductPage(Pageable pageable);
    ProductDTO getProductDetail(Long productId);

    ProductPageDTO getBrandProductPage(Long brandId, Pageable pageable);
    ProductPageDTO getStatusProductPage(StatusType status, Pageable pageable);
    ProductPageDTO getProductsBetweenDatetimePage(Date start, Date end, Pageable pageable);

    ProductDTO createProduct(ProductDTO productDTO, Long userIdx, String username);
    ProductDTO updateProduct(ProductDTO productDTO, Long userIdx, String username, Long productId);

    void deleteProduct(Long productId, Long userId);
}
