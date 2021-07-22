package com.choulatte.scentproduct.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pending_user")
public class PendingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pending_idx")
    private Long pendingUserId;

    @Column(nullable = false, name = "user_idx")
    private Long userId;

    @Column(name = "user_invalidation")
    private Boolean userInvalidation;
}
