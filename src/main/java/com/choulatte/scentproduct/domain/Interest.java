package com.choulatte.scentproduct.domain;

import com.choulatte.scentproduct.dto.InterestDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "interested_user")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_idx")
    private Long interestId;

    @Column(name = "validity")
    private Boolean validity;

    @Column(name = "user_idx")
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interesting_product")
    private Product product;

    @Column(nullable = false, name = "register_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDatetime;

    public InterestDTO toDTO() {
        return InterestDTO.builder().interestId(this.interestId)
                .userId(this.userId)
                .productId(this.product.getProductId())
                .registeredDatetime(this.registeredDatetime).build();
    }

    public Interest makeInvalid() {
        this.validity = false;
        return this;
    }
}
