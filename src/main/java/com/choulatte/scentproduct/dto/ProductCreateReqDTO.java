package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Brand;
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
public class ProductCreateReqDTO {
    private Long productIdx;
    private String productName;
    private String productDetail;
    private Long userIdx;
    private String username;
    private Double startingPrice;
    private Date startingDatetime;
    private Date endingDatetime;
    private Long brandId;

    public Product toEntity(Brand brand) {
        return Product.builder().productIdx(this.productIdx)
                .productName(this.productName)
                .productDetail(this.productDetail)
                .userIdx(this.userIdx).username(this.username)
                .startingPrice(this.startingPrice)
                .startingDatetime(this.startingDatetime)
                .endingDatetime(this.endingDatetime)
                .registeredDatetime(new Date())
                .lastModifiedDatetime(new Date())
                .validity(true).visibility(true)
                .status(Product.StatusType.REGISTERED)
                .brand(brand).build();
    }
}
