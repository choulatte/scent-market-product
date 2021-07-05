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
    private Long productIdx;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(length = 1000, nullable = false, name = "product_detail")
    private String productDetail;

    @Column(nullable = false, name = "writer_idx")
    private Long writerIdx;

    @Column(nullable = false, name = "writer_username")
    private String writerUsername;

    @Column(nullable = false, name = "start_price")
    private double startPrice;

    @Column(nullable = false, name = "register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "last_modified_date")
    private Date lastModifiedDate;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @Column( name = "interestedUser")
    private List<InterestedUser> interestedUsers = new ArrayList<>();
}
