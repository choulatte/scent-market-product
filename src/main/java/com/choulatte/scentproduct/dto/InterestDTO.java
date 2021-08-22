package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.Product;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InterestDTO {
    private Long interestIdx;
    private Long userIdx;
    private Long productIdx;
    private Date registeredDatetime;

    public Interest toEntity(Product product) {
        return Interest.builder().interestIdx(this.interestIdx)
                .userIdx(this.userIdx)
                .product(product)
                .registeredDatetime(new Date()).build();
    }
}
