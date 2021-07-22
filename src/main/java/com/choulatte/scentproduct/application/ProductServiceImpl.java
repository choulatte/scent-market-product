package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductPageDTO;
import com.choulatte.scentproduct.exception.BrandNotFoundException;
import com.choulatte.scentproduct.exception.PendingUserException;
import com.choulatte.scentproduct.exception.ProductNotFoundException;
import com.choulatte.scentproduct.exception.UserNotValidException;
import com.choulatte.scentproduct.repository.BrandRepository;
import com.choulatte.scentproduct.repository.PendingUserRepository;
import com.choulatte.scentproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final PendingUserRepository pendingUserRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, Long userId, String username) {
        if(verifyUserIsPending(userId)) throw new PendingUserException();
        return productRepository.save(productDTO.toEntity(getBrand(productDTO.getBrandId())).setRegisteredDatetime(new Date())).toDTO();
    }

    @Override
    public ProductPageDTO getUserProductPage(Long userId, Pageable pageable) {
        if(verifyUserIsPending(userId)) throw new PendingUserException();
        return getProductsPageDTO(productRepository.findAllByUserIdAndVisibilityTrue(userId, pageable), pageable);
    }

    @Override
    public ProductPageDTO getProductPage(Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByVisibilityIsTrue(pageable), pageable);
    }

    @Override
    public ProductDTO getProductDetail(Long productId) {
        return productRepository.findById(productId).map(Product::toDTO).orElseThrow(ProductNotFoundException::new);
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

    @Override
    public ProductDTO cancelProductBidding(Long productId, Long userId) {
        Product product = getProduct(productId);
        if(product.userIdIsEqual(userId))
            return productRepository.save(product.updateStatus(StatusType.CANCELLED).updateValidity(false)).toDTO();
        else throw new UserNotValidException();
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

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    private List<Product> getUserProducts(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    private List<Long> updateUserProductsStatus(Long userId, StatusType status) {
        List<Product> list = productRepository.saveAll(getUserProducts(userId).stream().map(product -> product.updateStatus(status)).collect(Collectors.toList()));
        return list.stream().map(Product::getProductId).collect(Collectors.toList());
    }


    private Boolean verifyUserIsPending(Long userId) {
        return pendingUserRepository.findByUserId(userId).isPresent();
    }

}
