package com.choulatte.scentproduct.dto;

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
public class ProductUpdateReqDTO {
    private Long productId;
    private String productName;
    private String productDetail;
    private Double startingPrice;
    private Date startingDatetime;
    private Date endingDatetime;
    private Long brandId;
    private Boolean visibility;
    private Product.StatusType status;
}
