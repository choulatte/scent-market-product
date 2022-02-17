package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.dto.InterestDTO;
import com.choulatte.scentproduct.dto.InterestReqDTO;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.exception.InterestNotFoundException;
import com.choulatte.scentproduct.exception.ProductNotFoundException;
import com.choulatte.scentproduct.repository.InterestRepository;
import com.choulatte.scentproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final ProductServiceImpl productService;

    @Override
    public InterestDTO createInterest(InterestReqDTO interestReqDTO, Long userId) {
        if(isInterestExist(interestReqDTO)) return getInterestByUserIdAndProductId(interestReqDTO.getUserId(), interestReqDTO.getProductId()).toDTO();
        return interestRepository.save(interestReqDTO.toEntity().createInterest(userId)).toDTO();
    }

    @Override
    public void deleteInterest(Long userId, Long productId) {
        productService.updateProductInterestCount(productId, Product.InterestOperation.DECREMENT);
        interestRepository.save(getInterestByUserIdAndProductId(userId, productId).makeInvalid());
    }

    @Override
    public List<Long> getInterestedUser(Long productId) {
        return interestRepository.findAllByProductIdAndValidityFalse(productId);
    }

    @Override
    public List<ProductDTO> getUserInterestingProduct(Long userId) {
        return interestRepository.findAllByUserId(userId).stream().map(i -> productService.getProductDetail(i.getProductId())).collect(Collectors.toList());
    }

    private Interest getInterestByUserIdAndProductId(Long userId, Long productId) {
        return interestRepository.findByUserIdAndProductId(userId, productId).orElseThrow(InterestNotFoundException::new);
    }

    public void deleteAllInterests(Long productId) {
        interestRepository.saveAll(interestRepository.findAllByProductId(productId).stream().map(Interest::makeInvalid).collect(Collectors.toList()));
    }

    private boolean isInterestExist(InterestReqDTO interestReqDTO) {
        return interestRepository.existsByUserIdAndProductIdAndValidityTrue(interestReqDTO.getUserId(), interestReqDTO.getProductId());
    }
}
