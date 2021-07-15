package com.choulatte.scentproduct.domain;

import com.choulatte.scentproduct.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_idx")
    private Long productId;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(length = 1000, nullable = false, name = "product_detail")
    private String productDetail;

    @Column(nullable = false, name = "user_idx")
    private Long userId;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "start_price")
    private Double startingPrice;

    @Column(nullable = false, name = "register_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "starting_datetime")
    private Date startingDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "ending_datetime")
    private Date endingDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "last_modified_datetime")
    private Date lastModifiedDatetime;

    @Column(nullable = false, name = "validation")
    private Boolean validity;

    @Column(nullable = false, name = "visibility")
    private Boolean visibility;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand")
    private Brand brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private final List<Image> images = new ArrayList<>();


    public Product setRegisteredDatetime(Date date) {
        this.registeredDatetime = date;
        return this;
    }

    public void updateStatus(StatusType status){
        this.status = status;
        this.lastModifiedDatetime = new Date();
    }

    public void updateValidity(Boolean validity) {
        this.validity = validity;
    }

    public void updateVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public void makeProductDelete(Boolean validity, Boolean visibility){
        this.validity = validity;
        this.visibility = visibility;
    }

    public ProductDTO toDTO() {
        return ProductDTO.builder().productId(this.productId)
                .productName(this.productName)
                .productDetail(this.productDetail)
                .userId(this.userId).username(this.username)
                .startingPrice(this.startingPrice)
                .startingDatetime(this.startingDatetime)
                .endingDatetime(this.endingDatetime)
                .registerDatetime(this.endingDatetime)
                .lastModifiedDatetime(this.lastModifiedDatetime)
                .validity(this.validity).visibility(this.visibility)
                .status(this.status)
                .brandId(this.brand.getBrandId()).build();
    }
}
