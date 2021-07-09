package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

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
    public ProductDTO createProduct(ProductDTO productDTO, Long userIdx, String username) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long userIdx, String username, Long productId) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).get();
    }
}
