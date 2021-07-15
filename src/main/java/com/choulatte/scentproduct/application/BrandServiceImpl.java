package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.Brand;
import com.choulatte.scentproduct.dto.BrandDTO;
import com.choulatte.scentproduct.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        return brandRepository.save(brandDTO.toEntity()).toDTO();
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO) {
        return brandRepository.save(brandDTO.toEntity()).toDTO();
    }

    @Override
    public List<BrandDTO> getBrands() {
        return brandRepository.findAll().stream().map(Brand::toDTO).collect(Collectors.toList());
    }
}
