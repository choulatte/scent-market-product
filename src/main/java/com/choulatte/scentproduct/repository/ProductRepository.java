package com.choulatte.scentproduct.repository;

import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
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
    Page<Product> findAllByBrandBrandIdAndVisibilityTrue(Long brandId, Pageable pageable);
    Page<Product> findAllByStatusAndVisibilityTrue(StatusType status, Pageable pageable);
    Page<Product> findAllByRegisteredDatetimeBetweenAndVisibilityTrue(Date start, Date end, Pageable pageable);

    List<Product> findAllByUserIdAndStatusNot(Long userId, StatusType status);
    List<Product> findAllByUserIdAndStatus(Long userID, StatusType status);

    Long countProductByUserIdAndStatusOrStatus(Long userId, StatusType status1, StatusType status2);
}
