package com.choulatte.scentproduct.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "brand")
public class Brand<BrandReposity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_idx")
    private Long brandId;

    @Column(nullable = false, name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
}
