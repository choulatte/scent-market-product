package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.dto.InterestDTO;
import com.choulatte.scentproduct.dto.ProductDTO;
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
        return new InterestDTO(interestRepository.save(Interest.newInstance(interestDTO, getProduct(interestDTO.getProductId()))));
    }

    @Override
    public void deleteInterest(Long userId, Long productId) {
        interestRepository.delete(getInterestByUserIdAndProductId(userId, productId));
    }

    @Override
    public List<Long> getInterestedUser(Long productId) {
        return null;
    }

    @Override
    public List<ProductDTO> getUserInterestingProduct(Long userId) {
        return interestRepository.findAllByUserId(userId).stream().map(Interest::getProduct)
                .collect(Collectors.toList()).stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    private Product getProduct(Long productId) {
        //TODO: 예외처리 관련 생각해보기
        return productRepository.findById(productId).orElseThrow(NullPointerException::new);
    }

    private Interest getInterestByUserIdAndProductId(Long userId, Long productId) {
        return interestRepository.findByUserIdAndProductProductId(userId, productId).orElseThrow(NullPointerException::new);
    }
}
