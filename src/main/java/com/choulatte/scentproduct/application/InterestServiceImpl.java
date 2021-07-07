package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {
    @Override
    public void setInterest(Long userIdx, Long productIdx) {

    }

    @Override
    public void deleteInterest(Long userIdx, Long productIdx) {

    }

    @Override
    public List<Long> getInterestedUser(Long productId) {
        return null;
    }

    @Override
    public List<ProductDTO> getUserInterestingProduct(Long userId) {
        return null;
    }
}
