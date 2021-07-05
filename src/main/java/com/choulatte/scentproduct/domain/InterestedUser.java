package com.choulatte.scentproduct.domain;

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
public class InterestedUser {

    @Id
    @Column(name = "interested_user_idx")
    private Long interestedUserIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interesting_product")
    private Product product;
}
