package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.ProductDetailResDTO;

public interface BiddingService {
    ProductDetailResDTO getProductDetail(Long productId);
}
