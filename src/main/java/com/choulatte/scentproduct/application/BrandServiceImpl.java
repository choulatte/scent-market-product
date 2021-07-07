package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.dto.BrandDTO;
import com.choulatte.scentproduct.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public void setBrand(BrandDTO brandDTO) {

    }

    @Override
    public void updateBrand(BrandDTO brandDTO) {

    }

    @Override
    public void deleteBrand(Long brandId) {

    }

    @Override
    public List<BrandDTO> getBrands() {
        return null;
    }
}
