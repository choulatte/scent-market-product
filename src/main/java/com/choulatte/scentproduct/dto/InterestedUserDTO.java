package com.choulatte.scentproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InterestedUserDTO {
    private Long interestedUserIdx;
    private ProductDTO product;
}
