package com.choulatte.scentproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductDetailResDTO {
    Date startingDatetime;
    Date endingDatetime;
    Long startingPrice;
}
