package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InterestReqDTO {
    private Long userId;
    private Long productId;

    public Interest toEntity() {
        return Interest.builder()
                .validity(true)
                .userId(this.userId)
                .productId(this.productId)
                .registeredDatetime(new Date()).build();
    }
}
