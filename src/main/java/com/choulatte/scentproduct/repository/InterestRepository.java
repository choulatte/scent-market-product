package com.choulatte.scentproduct.repository;

import com.choulatte.scentproduct.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findAll();
    Optional<Interest> findByUserIdAndProductProductId(Long userId, Long productId);
    List<Interest> findAllByUserId(Long userId);
    List<Long> findAllByProductProductId(Long productId);
}
