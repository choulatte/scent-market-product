package com.choulatte.scentproduct.dto;

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
    private List<InterestDTO> interests;
    private BrandDTO brand;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productDetail = product.getProductDetail();
        this.userId = product.getUserId();
        this.username = product.getUsername();
        this.startingPrice = product.getStartingPrice();
        this.registerDatetime = product.getRegisteredDatetime();
        this.lastModifiedDatetime = product.getLastModifiedDatetime();
        this.startingDatetime = product.getStartingDatetime();
        this.endingDatetime = product.getEndingDatetime();
        this.validity = product.getValidity();
        this.visibility = product.getVisibility();
        this.status = product.getStatus();
        this.brand = new BrandDTO(product.getBrand());
    }
}
