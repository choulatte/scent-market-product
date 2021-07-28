package com.choulatte.scentproduct.domain;

import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductUpdateReqDTO;
import com.choulatte.scentproduct.exception.ProductBadRequestException;
import com.choulatte.scentproduct.exception.ProductIllegalStateException;
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


    public Product createProduct(Long userId, String username) {
        if(!this.userId.equals(userId) || !this.username.equals(username)) throw new ProductBadRequestException();
        if(!(new Date().getTime() < this.registeredDatetime.getTime() && this.registeredDatetime.getTime() < this.startingDatetime.getTime() && this.startingDatetime.getTime() < this.endingDatetime.getTime()))
            throw new ProductIllegalStateException();

        return this;
    }

    public Product updateProduct(ProductUpdateReqDTO productUpdateReqDTO, Brand brand, Long userId, String username) {
        if(!this.userId.equals(userId) || !this.username.equals(username)) throw new ProductBadRequestException();
        if(!(this.status.equals(StatusType.REGISTERED) || this.status.equals(StatusType.CANCELLED))) throw new ProductIllegalStateException();
        this.productName = productUpdateReqDTO.getProductName();
        this.productDetail = productUpdateReqDTO.getProductDetail();
        this.startingPrice = productUpdateReqDTO.getStartingPrice();
        this.startingDatetime = productUpdateReqDTO.getStartingDatetime();
        this.endingDatetime = productUpdateReqDTO.getEndingDatetime();
        this.visibility = productUpdateReqDTO.getVisibility();
        this.lastModifiedDatetime = new Date();

        return this;
    }

    public Product makeProductOngoing() {
        if(!this.status.equals(StatusType.REGISTERED)) throw new ProductIllegalStateException();
        this.status = StatusType.ONGOING;
        this.lastModifiedDatetime = new Date();
        return this;
    }

    public Product makeProductClosed() {
        if(!this.status.equals(StatusType.ONGOING)) throw new ProductIllegalStateException();
        this.status = StatusType.CLOSED;
        this.lastModifiedDatetime = new Date();

        return this;
    }

    public Product makeProductCancel(Long productId, Long userId) {
        if(!this.userId.equals(userId) || !this.productId.equals(productId)) throw new ProductBadRequestException();
        if(!(this.status.equals(StatusType.REGISTERED) || this.status.equals(StatusType.ONGOING) || this.status.equals(StatusType.CONTRACTING))) throw new ProductIllegalStateException();
        this.status = StatusType.CANCELLED;
        this.lastModifiedDatetime = new Date();

        return this;
    }

    //TODO("Contracting&Contracted")

    public Product makeProductPending() {
        if(this.status.equals(StatusType.ONGOING) || this.status.equals(StatusType.CONTRACTING)) throw new ProductIllegalStateException();
        this.status = StatusType.PENDING;
        this.visibility = false;
        this.lastModifiedDatetime = new Date();

        return this;
    }

    public Product makeProductDelete(Long productId, Long userId){
        if(!this.userId.equals(userId) || !this.productId.equals(productId)) throw new ProductBadRequestException();

        this.validity = false;
        this.visibility = false;
        this.status = StatusType.DELETED;
        this.lastModifiedDatetime = new Date();
        return this;
    }
    public Product updateStatus(StatusType status){
        this.status = status;
        this.lastModifiedDatetime = new Date();
        return this;
    }

    public Boolean userIdIsEqual(Long userId) {
        return this.userId.equals(userId);
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
