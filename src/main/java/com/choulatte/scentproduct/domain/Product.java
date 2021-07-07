package com.choulatte.scentproduct.domain;

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
    private Boolean isValid;

    @Column(nullable = false, name = "visibility")
    private Boolean isVisible;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private final List<Interest> interests = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand")
    private Brand brand;

    public void changeStatus(StatusType status){
        this.status = status;
        this.lastModifiedDatetime = new Date();
    }
}
