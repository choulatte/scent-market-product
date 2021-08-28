package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.domain.Product;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "상품 번호", position = 0)
    private Long productId;

    @ApiModelProperty(value = "상품 이름", example = "판매하려는 상품 이름", required = true, position = 1)
    private String productName;

    @ApiModelProperty(value = "상품 상세", example = "상품에 대한 상세 설명. Ex) 구매 일자, 사용 용량 등", required = true, position = 2)
    private String productDetail;

    @ApiModelProperty(value = "판매 회원 고유 식별 번호", required = true, position = 3)
    private Long userId;

    @ApiModelProperty(value = "판매 회원 이름", required = true, example = "홍길동", position = 4)
    private String username;

    @ApiModelProperty(value = "시작가", required = true, example = "100000", notes = "정해진 호가 단위에 따라야 합니다.", position = 5)
    private Double startingPrice;

    @ApiModelProperty(value = "경매 시작 시간", required = true, position = 6)
    private Date startingDatetime;

    @ApiModelProperty(value = "경매 종료 시간", required = true, position = 7)
    private Date endingDatetime;

    @ApiModelProperty(value = "상품 브랜드 번호", position = 8)
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
                .status(Product.StatusType.REGISTERED)
                .brand(brand).build();
    }
}
