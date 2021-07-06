package com.choulatte.scentproduct.application;

import java.util.List;

public interface InterestService {
    void setInterest(Long userIdx, Long productIdx);
    void deleteInterest(Long userIdx, Long productIdx);
    List<Long> getInterestedUserList(Long productIdx);
    List<Long> getUserInterestingProductList(Long userIdx);
}
