package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductCreateReqDTO {
    private Long productId;
    private String productName;
    private String productDetail;
    private Long userId;
    private String username;
    private Double startingPrice;
    private Date startingDatetime;
    private Date endingDatetime;
    private Long brandId;

    public Product toEntity(Brand brand) {
        return Product.builder().productId(this.productId)
                .productName(this.productName)
                .productDetail(this.productDetail)
                .userId(this.userId).username(this.username)
                .startingPrice(this.startingPrice)
                .startingDatetime(this.startingDatetime)
                .endingDatetime(this.endingDatetime)
                .registeredDatetime(new Date())
                .lastModifiedDatetime(new Date())
                .validity(true).visibility(true)
                .status(StatusType.REGISTERED)
                .brand(brand).build();
    }
}
