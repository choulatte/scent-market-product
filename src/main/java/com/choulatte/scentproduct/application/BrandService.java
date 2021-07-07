package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.BrandDTO;

import java.util.List;


public interface BrandService {
    void setBrand(BrandDTO brandDTO);
    void updateBrand(BrandDTO brandDTO);
    void deleteBrand(Long brandId);
    List<BrandDTO> getBrands();
}
