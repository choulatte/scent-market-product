package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductDTO> getUserProductsList(Long userId) {
        return null;
    }

    @Override
    public Optional<ProductDTO> getUserProductDetail(Long productId) {
        return Optional.empty();
    }

    @Override
    public List<ProductDTO> getProductsList() {
        return null;
    }

    @Override
    public Optional<ProductDTO> getProductDetail(Long productId) {
        return Optional.empty();
    }

    @Override
    public List<ProductDTO> getBrandProducts(Long brandId) {
        return null;
    }

    @Override
    public List<ProductDTO> getStatusProducts(StatusType status) {
        return null;
    }

    @Override
    public void setProduct(ProductDTO productDTO, Long userIdx, String username) {

    }

    @Override
    public void setProduct(ProductDTO productDTO, Long userIdx, String username, Long productId) {

    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
