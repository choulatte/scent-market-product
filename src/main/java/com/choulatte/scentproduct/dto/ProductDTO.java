package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.domain.StatusType;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private Boolean isValid;
    private Boolean isVisible;
    private StatusType status;
    private List<Interest> interests;
    private BrandDTO brand;
}
