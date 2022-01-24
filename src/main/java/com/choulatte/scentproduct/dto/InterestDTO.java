package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.Product;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InterestDTO {
    private Long interestId;
    private Long userId;
    private Long productId;
    private Date registeredDatetime;

    public InterestDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Interest toEntity(Product product) {
        return Interest.builder().interestId(this.interestId)
                .validity(true)
                .userId(this.userId)
                .product(product)
                .registeredDatetime(new Date()).build();
    }
}
