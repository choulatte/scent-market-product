package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Interest;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InterestDTO {
    private Long interestedUserId;
    private ProductDTO product;

    public InterestDTO(Interest interest) {
        this.interestedUserId = interest.getInterestedUserId();
        this.product = new ProductDTO(interest.getProduct());
    }
}
