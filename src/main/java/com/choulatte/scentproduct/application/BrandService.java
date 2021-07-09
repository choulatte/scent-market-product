package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.BrandDTO;

import java.util.List;


public interface BrandService {
    BrandDTO createBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(BrandDTO brandDTO);
    List<BrandDTO> getBrands();
}
