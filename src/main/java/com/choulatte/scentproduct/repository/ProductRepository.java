package com.choulatte.scentproduct.repository;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import javafx.beans.property.ListProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByVisibilityIsTrue();
    List<Product> findAllByUserIdAndVisibilityTrue(Long userId);
    List<Product> findAllByBrandBrandIdAndVisibilityTrue(Long brandId);
    List<Product> findAllByStatusAndVisibilityTrue(StatusType status);
    Optional<Product> findByProductIdAndUserIdAndVisibilityTrue(Long productId, Long userId);
    List<Product> findAllByRegisteredDatetimeBetweenAndVisibilityTrue(Date start, Date end);
}
