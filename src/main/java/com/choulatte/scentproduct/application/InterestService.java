package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.ProductDTO;

import java.util.List;

public interface InterestService {
    void setInterest(Long userId, Long productId);
    void deleteInterest(Long userId, Long productId);
    List<Long> getInterestedUser(Long productId);
    List<ProductDTO> getUserInterestingProduct(Long userId);
}
