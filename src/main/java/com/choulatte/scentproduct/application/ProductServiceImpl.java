package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductPageDTO;
import com.choulatte.scentproduct.exception.BrandNotFoundException;
import com.choulatte.scentproduct.repository.BrandRepository;
import com.choulatte.scentproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ProductPageDTO getUserProductPage(Long userId, Pageable pageable) {

        return getProductsPageDTO(productRepository.findAllByUserIdAndVisibilityTrue(userId, pageable), pageable);
    }

    @Override
    public ProductPageDTO getProductPage(Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByVisibilityIsTrue(pageable), pageable);
    }

    @Override
    public Optional<ProductDTO> getProductDetail(Long productId) {
        return productRepository.findById(productId).map(Product::toDTO);
    }

    @Override
    public ProductPageDTO getBrandProductPage(Long brandId, Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByBrandBrandIdAndVisibilityTrue(brandId, pageable), pageable);
    }

    @Override
    public ProductPageDTO getStatusProductPage(StatusType status, Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByStatusAndVisibilityTrue(status, pageable), pageable);
    }

    @Override
    public ProductPageDTO getProductsBetweenDatetimePage(Date start, Date end, Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByRegisteredDatetimeBetweenAndVisibilityTrue(start, end, pageable), pageable);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long userId, String username, Long productId) {
        return productRepository.save(productDTO.toEntity(getBrand(productDTO.getBrandId()))).toDTO();
    }

    @Override
    public void deleteProduct(Long productId, Long userId) {
        productRepository.findByProductIdAndUserIdAndVisibilityTrue(productId, userId).orElseThrow(RuntimeException::new).makeProductDelete(false, false);
    }

    private ProductPageDTO getProductsPageDTO(Page<Product> products, Pageable pageable) {
        List<ProductDTO> productDTOs = products.getContent().stream().map(Product::toDTO).collect(Collectors.toList());

        return ProductPageDTO.builder()
                .totalPage(products.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .products(productDTOs).build();
    }

    private Brand getBrand(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(BrandNotFoundException::new);
    }
}
