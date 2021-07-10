package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.domain.StatusType;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDetail;
    private Long userId;
    private String username;
    private Double startingPrice;
    private Date registerDatetime;
    private Date lastModifiedDatetime;
    private Date startingDatetime;
    private Date endingDatetime;
    private Boolean validity;
    private Boolean visibility;
    private StatusType status;
    private List<Long> interests;
    private Long brandId;

    public Product toEntity(Brand brand) {
        return Product.builder().productId(this.productId)
                .productName(this.productName)
                .productDetail(this.productDetail)
                .userId(this.userId).username(this.username)
                .startingPrice(this.startingPrice)
                .startingDatetime(this.startingDatetime)
                .endingDatetime(this.endingDatetime)
                .registeredDatetime(this.registerDatetime)
                .lastModifiedDatetime(new Date())
                .validity(this.validity).visibility(this.visibility)
                .status(this.status)
                .brand(brand).build();
    }
}
