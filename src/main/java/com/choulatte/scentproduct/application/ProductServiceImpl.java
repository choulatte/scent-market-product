package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.repository.BrandRepository;
import com.choulatte.scentproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, Long userIdx, String username) {
        return productRepository.save(productDTO.toEntity(getBrand(productDTO.getBrandId())).setRegisteredDatetime(new Date())).toDTO();
    }

    @Override
    public List<ProductDTO> getUserProductsList(Long userId) {
        return productRepository.findAllByUserIdAndVisibilityTrue(userId).stream().map(Product::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsList() {
        return productRepository.findAllByVisibilityIsTrue().stream().map(Product::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductDetail(Long productId) {
        return productRepository.findById(productId).map(Product::toDTO);
    }

    @Override
    public List<ProductDTO> getBrandProducts(Long brandId) {
        return productRepository.findAllByBrandBrandIdAndVisibilityTrue(brandId).stream().map(Product::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getStatusProducts(StatusType status) {
        return productRepository.findAllByStatusAndVisibilityTrue(status).stream().map(Product::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsBetweenDatetime(Date start, Date end) {
        return productRepository.findAllByRegisteredDatetimeBetweenAndVisibilityTrue(start, end).stream().map(Product::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long userId, String username, Long productId) {
        return productRepository.save(productDTO.toEntity(getBrand(productDTO.getBrandId()))).toDTO();
    }

    @Override
    public void deleteProduct(Long productId, Long userId) {
        productRepository.findByProductIdAndUserIdAndVisibilityTrue(productId, userId).orElseThrow(NullPointerException::new).makeProductDelete(false, false);
    }

    private Brand getBrand(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(NullPointerException::new);
    }
}
