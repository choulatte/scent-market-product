package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.dto.ProductCreateReqDTO;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductPageDTO;
import com.choulatte.scentproduct.dto.ProductUpdateReqDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ProductService {
    ProductPageDTO getUserProductPage(Long userId, Pageable pageable);
    ProductPageDTO getProductPage(Pageable pageable);
    ProductDTO getProductDetail(Long productId);

    ProductPageDTO getBrandProductPage(String brandName, Pageable pageable);
    ProductPageDTO getStatusProductPage(Product.StatusType status, Pageable pageable);
    ProductPageDTO getProductsBetweenDatetimePage(Date start, Date end, Pageable pageable);

    ProductDTO createProduct(ProductCreateReqDTO ProductCreateReqDTO, Long userIdx, String username);
    ProductDTO updateProduct(ProductUpdateReqDTO productUpdateReqDTO, Long userIdx, String username, Long productId);

    void deleteProduct(Long productId, Long userId);

    ProductDTO cancelProductBidding(Long productId, Long userId);
    List<Long> makeProductPending(Long userId);
    void makeProductsInvalid(Long userId);
    Boolean checkUserProductOngoing(Long userId);
    List<Long> getConflictProducts(Long userId);
    List<Long> releaseProductPending(Long userId);

}
