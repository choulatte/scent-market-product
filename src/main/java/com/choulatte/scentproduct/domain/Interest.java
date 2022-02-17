package com.choulatte.scentproduct.domain;

import com.choulatte.scentproduct.dto.InterestDTO;
import com.choulatte.scentproduct.dto.InterestReqDTO;
import com.choulatte.scentproduct.exception.UserNotValidException;
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

    @Column(name = "interesting_product_idx")
    private Long productId;

    @Column(nullable = false, name = "register_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDatetime;

    public Interest createInterest(Long userId) {
        if(!this.userId.equals(userId)) throw new UserNotValidException();
        this.validity = true;
        this.registeredDatetime = new Date();
        return this;
    }

    public Interest makeInvalid() {
        this.validity = false;
        return this;
    }

    public InterestDTO toDTO() {
        return InterestDTO.builder()
                .interestId(this.interestId)
                .productId(this.productId)
                .userId(this.userId)
                .registeredDatetime(this.registeredDatetime)
                .build();
    }
}
