package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.BrandService;
import com.choulatte.scentproduct.dto.BrandDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/brands")
public class BrandController {

    private final BrandService brandService;

    @GetMapping(value = "")
    ResponseEntity<List<BrandDTO>> getBrandList() { return ResponseEntity.ok(brandService.getBrands()); }

    @PostMapping(value = "")
    ResponseEntity<BrandDTO> setBrand(BrandDTO brandDTO) {
        return ResponseEntity.ok(brandService.createBrand(brandDTO));
    }

    @PutMapping(value = "")
    ResponseEntity<BrandDTO> updateBrand(BrandDTO brandDTO) {
        return ResponseEntity.ok(brandService.updateBrand(brandDTO));
    }

}
