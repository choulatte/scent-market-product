package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.InterestService;
import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.dto.InterestDTO;
import com.choulatte.scentproduct.dto.InterestReqDTO;
import com.choulatte.scentproduct.dto.ProductDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "사용자별 관심 상품 조회", notes = "각 사용자별로 관심있는 상품의 목록을 조회합니다.")
    @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)")
    ResponseEntity<List<ProductDTO>> getUserInterestingProducts(@RequestHeader("User-Idx") Long userId) {
        return ResponseEntity.ok(interestService.getUserInterestingProduct(userId));
    }

    @PostMapping(value = "")
    @ApiOperation(value = "관심 상품 등록", notes = "관심 상품을 등록합니다.")
    @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)")
    void setInterest(@RequestBody InterestReqDTO interestReqDTO, @RequestHeader("User-Idx") Long userId) {
        interestService.createInterest(interestReqDTO, userId);
    }

    @DeleteMapping(value = "/{productId}")
    @ApiOperation(value = "관심 상품 삭제", notes = "등록한 관심 상품을 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)", required = true),
            @ApiImplicitParam(name = "productId", value = "상품 번호", required = true)
    })
    void deleteInterest(@PathVariable("productId") Long productId, @RequestHeader("User-Idx") Long userId) {
        interestService.deleteInterest(userId, productId);
    }

}
