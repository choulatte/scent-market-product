package com.choulatte.scentproduct.domain;

import com.choulatte.scentproduct.dto.BrandDTO;
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
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_idx")
    private Long brandId;

    @Column(nullable = false, name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private final List<Product> products = new ArrayList<>();

    public BrandDTO toDTO() {
        return BrandDTO.builder().brandId(this.brandId)
                .brandName(this.brandName).build();
    }
}
