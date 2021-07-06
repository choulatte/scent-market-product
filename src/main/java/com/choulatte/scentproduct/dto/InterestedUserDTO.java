package com.choulatte.scentproduct.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InterestedUserDTO {
    private Long interestedUserId;
    private ProductDTO product;
}
