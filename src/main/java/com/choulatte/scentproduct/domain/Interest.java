package com.choulatte.scentproduct.domain;

import com.choulatte.scentproduct.dto.InterestDTO;
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

    @Column(name = "user_idx")
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interesting_product")
    private Product product;

    @Column(nullable = false, name = "register_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDatetime;

    public static Interest newInstance(InterestDTO interestDTO, Product product) {
        return Interest.builder().userId(interestDTO.getUserId())
                .product(product)
                .registeredDatetime(new Date())
                .build();
    }
}
