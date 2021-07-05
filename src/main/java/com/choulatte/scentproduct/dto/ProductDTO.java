package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.InterestedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long productIdx;
    private String productName;
    private String productDetail;
    private Long writerIdx;
    private String writerUsername;
    private double startPrice;
    private Date registerDate;
    private Date lastModifiedDate;
    private List<InterestedUser> interestedUsers;
}
