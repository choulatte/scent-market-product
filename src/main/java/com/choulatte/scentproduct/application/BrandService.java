package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.dto.BrandCreateAndUpdateReqDTO;
import com.choulatte.scentproduct.dto.BrandDTO;

import java.util.List;


public interface BrandService {
    BrandDTO createBrand(BrandCreateAndUpdateReqDTO brandCreateAndUpdateReqDTO);
    BrandDTO updateBrand(BrandCreateAndUpdateReqDTO brandCreateAndUpdateReqDTO);
    List<BrandDTO> getBrands();
}
