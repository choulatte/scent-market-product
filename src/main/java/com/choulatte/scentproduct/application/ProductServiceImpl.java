package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.dto.ProductCreateReqDTO;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductPageDTO;
import com.choulatte.scentproduct.dto.ProductUpdateReqDTO;
import com.choulatte.scentproduct.exception.BrandNotFoundException;
import com.choulatte.scentproduct.exception.OngoingProductPresentException;
import com.choulatte.scentproduct.exception.PendingUserException;
import com.choulatte.scentproduct.exception.ProductNotFoundException;
import com.choulatte.scentproduct.repository.BrandRepository;
import com.choulatte.scentproduct.repository.PendingUserRepository;
import com.choulatte.scentproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    private final PendingService pendingService;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "product", key = "#productCreateReqDTO.productId"),
            @CacheEvict(value = "userProducts", key = "#userId"),
            @CacheEvict(value = "allProducts", allEntries = true),
            @CacheEvict(value = "statusProducts", key = "T(com.choulatte.scentproduct.domain.Product$StatusType).REGISTERED"),
            @CacheEvict(value = "datetimeProducts", allEntries = true)},
            put = {
            @CachePut(value = "product", key = "#result.productId")
            })
    public ProductDTO createProduct(ProductCreateReqDTO productCreateReqDTO, Long userId, String username) {
        if(pendingService.isPresent(userId)) throw new PendingUserException();
        return productRepository.save(productCreateReqDTO.toEntity(getBrand(productCreateReqDTO.getBrandId())).createProduct(userId, username)).toDTO();
    }

    @Override
    @Cacheable(value = "userProducts", key = "#userId", condition = "#pageable.pageNumber == 0")
    public ProductPageDTO getUserProductPage(Long userId, Pageable pageable) {
        if(pendingService.isPresent(userId)) throw new PendingUserException();
        return getProductsPageDTO(productRepository.findAllByUserIdAndVisibilityTrue(userId, pageable), pageable);
    }


    @Override
    @Cacheable(value = "allProducts", key = "0", condition = "#pageable.pageNumber == 0")
    public ProductPageDTO getProductPage(Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByVisibilityIsTrue(pageable), pageable);
    }

    @Override
    @Cacheable(value = "product", key = "#productId")
    public ProductDTO getProductDetail(Long productId) {
        return productRepository.findById(productId).map(Product::toDTO).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Cacheable(value = "brandProducts", key = "#brandName", condition = "#pageable.pageNumber == 0")
    public ProductPageDTO getBrandProductPage(String brandName, Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByBrandBrandNameAndVisibilityTrue(brandName, pageable), pageable);
    }

    @Override
    @Cacheable(value = "statusProducts", key = "#status", condition = "#pageable.pageNumber == 0")
    public ProductPageDTO getStatusProductPage(Product.StatusType status, Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByStatusAndVisibilityTrue(status, pageable), pageable);
    }

    @Cacheable(value = "datetimeProducts", key = "#start + '.' + #end", condition = "#pageable.pageNumber == 0")
    @Override
    public ProductPageDTO getProductsBetweenDatetimePage(Date start, Date end, Pageable pageable) {
        return getProductsPageDTO(productRepository.findAllByRegisteredDatetimeBetweenAndVisibilityTrue(start, end, pageable), pageable);
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "product", key = "#productUpdateReqDTO.productId"),
            @CacheEvict(value = "userProducts", key = "#userId"),
            @CacheEvict(value = "allProducts", allEntries = true),
            @CacheEvict(value = "statusProducts", key = "#result.status"),
            @CacheEvict(value = "datetimeProducts", allEntries = true)},
            put = {
                    @CachePut(value = "product", key = "#result.productId")
            })
    public ProductDTO updateProduct(ProductUpdateReqDTO productUpdateReqDTO, Long userId, String username, Long productId) {
        return productRepository.save
                (getProduct(productUpdateReqDTO.getProductId()).updateProduct(productUpdateReqDTO, getBrand(productUpdateReqDTO.getBrandId()), userId, username)).toDTO();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "product", key = "#productId"),
            @CacheEvict(value = "userProducts", key = "#userId"),
            @CacheEvict(value = "allProducts", allEntries = true),
            @CacheEvict(value = "statusProducts", allEntries = true), // ?????? ????????? ????????? ???????????? ??? ??? ??????. ??????????????? dto ???????????????
            @CacheEvict(value = "datetimeProducts", allEntries = true)
    })
    public void deleteProduct(Long productId, Long userId) {
        // TODO: 2022-02-17 interest ????????? ????????? ??????????????? 
        //interestService.deleteAllInterests(productId);
        productRepository.save(
                productRepository.findByProductIdAndUserIdAndVisibilityTrue(productId, userId).orElseThrow(RuntimeException::new).makeProductDelete(productId, userId)
        );
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "allProducts", allEntries = true),
            @CacheEvict(value = "statusProducts", allEntries = true), // ?????? ????????? ????????? ???????????? ??? ??? ??????. ??????????????? dto ???????????????. ?????? ????????? 3????????? ??? ??? ?????? ??????
            @CacheEvict(value = "datetimeProducts", allEntries = true)
    })
    public ProductDTO cancelProductBidding(Long productId, Long userId) {
        Product product = getProduct(productId);
        return productRepository.save(product.makeProductCancel(productId, userId)).toDTO();
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

    private List<Product> getUserProducts(Long userId) {
        return productRepository.findAllByUserIdAndStatusNot(userId, Product.StatusType.DELETED);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public void updateProductInterestCount(Long productId, Product.InterestOperation operation) {
        productRepository.save(getProduct(productId).updateInterestCount(operation));
    }

    public List<Long> makeProductPending(Long userId) {
        List<Product> list = productRepository.saveAll(getUserProducts(userId).stream().map(Product::makeProductPending).collect(Collectors.toList()));
        return list.stream().map(Product::getProductId).collect(Collectors.toList());
    }

    @Override // Delete??? ???????????? ???????????????~
    @Caching(evict = {
            // @CacheEvict(value = "product", key = "#productId"),
            @CacheEvict(value = "userProducts", key = "#userId"),
            @CacheEvict(value = "allProducts", allEntries = true),
            @CacheEvict(value = "statusProducts", allEntries = true), // ?????? ????????? ????????? ???????????? ??? ??? ??????. ??????????????? dto ???????????????
            @CacheEvict(value = "datetimeProducts", allEntries = true)
    })
    public void makeProductsInvalid(Long userId) {
        List<Product> userProducts = getUserProducts(userId);

        productRepository.saveAll(userProducts.stream().map(product -> product.makeProductDelete(product.getProductId(), userId)).collect(Collectors.toList()));
    }

    public Boolean checkUserProductOngoing(Long userId) {
        //TODO("Need to test And(A Or B)")
        if(productRepository.countProductByUserIdAndStatusOrStatus(userId, Product.StatusType.ONGOING, Product.StatusType.CONTRACTING).equals(0L)) {
            return true;
        }
        else throw new OngoingProductPresentException();
    }

    @Override
    public List<Long> getConflictProducts(Long userId) {
        return productRepository.findAllByUserIdAndStatusOrStatus(userId, Product.StatusType.ONGOING, Product.StatusType.CONTRACTING);
    }

    @Override
    public List<Long> releaseProductPending(Long userId) {
        List<Product> products = productRepository.findAllByUserIdAndStatus(userId, Product.StatusType.PENDING);
        return productRepository.saveAll(products.stream().map(Product::releasePending).collect(Collectors.toList()))
                .stream().map(Product::getProductId).collect(Collectors.toList());
    }

}
