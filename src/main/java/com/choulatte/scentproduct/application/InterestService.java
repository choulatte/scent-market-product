package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.InterestDTO;
import com.choulatte.scentproduct.dto.InterestReqDTO;
import com.choulatte.scentproduct.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface InterestService {
    InterestDTO createInterest(InterestReqDTO interestReqDTO, Long userId);
    void deleteInterest(Long userId, Long productId);
    List<Long> getInterestedUser(Long productId);
    List<ProductDTO> getUserInterestingProduct(Long userId);
}
