package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.dto.InterestDTO;
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
    private final ProductRepository productRepository;

    @Override
    public InterestDTO createInterest(InterestDTO interestDTO) {
        return interestRepository.save(interestDTO.toEntity(getProduct(interestDTO.getProductId()))).toDTO();
    }

    @Override
    public void deleteInterest(Long userId, Long productId) {
        interestRepository.delete(getInterestByUserIdAndProductId(userId, productId));
    }

    @Override
    public List<Long> getInterestedUser(Long productId) {
        return interestRepository.findAllByProductProductId(productId);
    }

    @Override
    public List<ProductDTO> getUserInterestingProduct(Long userId) {
        return interestRepository.findAllByUserId(userId).stream().map(Interest::getProduct)
                .collect(Collectors.toList()).stream().map(Product::toDTO).collect(Collectors.toList());
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    private Interest getInterestByUserIdAndProductId(Long userId, Long productId) {
        return interestRepository.findByUserIdAndProductProductId(userId, productId).orElseThrow(InterestNotFoundException::new);
    }
}
