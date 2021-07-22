package com.choulatte.scentproduct.repository;

import com.choulatte.scentproduct.domain.PendingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendingUserRepository extends JpaRepository<PendingUser, Long> {

    Optional<PendingUser> findByUserId(Long userId);
}
