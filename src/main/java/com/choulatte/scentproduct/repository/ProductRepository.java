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
    List<Product> findAll();
    List<Product> findAllByUserId(Long userId);
    List<Product> findAllByBrandBrandId(Long brandId);
    List<Product> findAllByStatus(StatusType status);
    Optional<Product> findByProductIdAndUserId(Long productId, Long userId);
    List<Product> findAllByRegisteredDatetimeBetween(Date start, Date end);
}
