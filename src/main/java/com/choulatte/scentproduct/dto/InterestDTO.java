package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Interest;
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

    public InterestDTO(Interest interest) {
        this.interestId = interest.getInterestId();
        this.userId = interest.getUserId();
        this.productId = interest.getProduct().getProductId();
        this.registeredDatetime = interest.getRegisteredDatetime();
    }
}
