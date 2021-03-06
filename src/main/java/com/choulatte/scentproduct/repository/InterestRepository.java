package com.choulatte.scentproduct.repository;

import com.choulatte.scentproduct.domain.Interest;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findAll();
    Optional<Interest> findByUserIdAndProductId(Long userId, Long productId);
    List<Interest> findAllByUserId(Long userId);
    List<Interest> findAllByProductId(Long productId);
    List<Long> findAllByProductIdAndValidityFalse(Long productId);
    Boolean existsByUserIdAndProductIdAndValidityTrue(Long userId, Long productId);
}
