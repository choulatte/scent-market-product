package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.InterestService;
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

    @GetMapping(value = "")
    ResponseEntity<List<ProductDTO>> getUserInterestingProducts(@RequestHeader("userId") String userId) {
        return ResponseEntity.ok(interestService.getUserInterestingProduct(Long.parseLong(userId)));
    }

    @PostMapping(value = "/{id}")
    void setInterest(@PathVariable("id") Long id, @RequestHeader("userId") String userId) {
        interestService.setInterest(Long.parseLong(userId), id);
    }

    @DeleteMapping(value = "/{id}")
    void deleteInterest(@PathVariable("id") Long id, @RequestHeader("userId") String userId) {
        interestService.deleteInterest(Long.parseLong(userId), id);
    }

}
