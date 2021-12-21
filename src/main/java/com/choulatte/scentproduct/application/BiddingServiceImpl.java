package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.dto.ProductDetailResDTO;
import com.choulatte.scentproduct.exception.ProductBadRequestException;
import com.choulatte.scentproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BiddingServiceImpl implements BiddingService {

    private final ProductRepository productRepository;

    @Override
    public ProductDetailResDTO getProductDetail(Long productId) {
        return getProduct(productId).toProductDetailResDTO();
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductBadRequestException::new);
    }
}
