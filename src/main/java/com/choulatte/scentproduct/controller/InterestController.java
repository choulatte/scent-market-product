package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.InterestService;
import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.domain.Interest;
import com.choulatte.scentproduct.dto.InterestDTO;
import com.choulatte.scentproduct.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/interest")
public class InterestController {

    private final InterestService interestService;
    private final ProductService productService;

    @GetMapping(value = "")
    ResponseEntity<List<ProductDTO>> getUserInterestingProducts(@RequestHeader("userId") String userId) {
        return ResponseEntity.ok(interestService.getUserInterestingProduct(Long.parseLong(userId)));
    }

    @PostMapping(value = "")
    void setInterest(@RequestBody InterestDTO interestDTO) {
        interestService.createInterest(interestDTO);
    }

    @DeleteMapping(value = "/{id}")
    void deleteInterest(@PathVariable("id") Long id, @RequestHeader("userId") String userId) {
        interestService.deleteInterest(Long.parseLong(userId), id);
    }

}
