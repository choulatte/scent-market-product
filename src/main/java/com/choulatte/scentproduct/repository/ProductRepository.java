package com.choulatte.scentproduct.repository;

import com.choulatte.scentproduct.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIdAndUserIdAndVisibilityTrue(Long productId, Long userId);

    Page<Product> findAllByVisibilityIsTrue(Pageable pageable);
    Page<Product> findAllByUserIdAndVisibilityTrue(Long userId, Pageable pageable);
    Page<Product> findAllByBrandBrandNameAndVisibilityTrue(String brandName, Pageable pageable);
    Page<Product> findAllByStatusAndVisibilityTrue(Product.StatusType status, Pageable pageable);
    Page<Product> findAllByRegisteredDatetimeBetweenAndVisibilityTrue(Date start, Date end, Pageable pageable);

    List<Product> findAllByUserIdAndStatusNot(Long userId, Product.StatusType status);
    List<Product> findAllByUserIdAndStatus(Long userId, Product.StatusType status);
    List<Long> findAllByUserIdAndStatusOrStatus(Long userID, Product.StatusType status1, Product.StatusType status2);

    Long countProductByUserIdAndStatusOrStatus(Long userId, Product.StatusType status1, Product.StatusType status2);
}
