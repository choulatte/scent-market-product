package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.domain.Product;
import com.choulatte.scentproduct.dto.ProductCreateReqDTO;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductPageDTO;
import com.choulatte.scentproduct.dto.ProductUpdateReqDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "상품 등록", notes = "경매에 올릴 상품을 등록합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)", required = true),
            @ApiImplicitParam(name = "Username", value = "사용자 이름", required = true)
    })
    ResponseEntity<ProductDTO> setProduct(@RequestBody ProductCreateReqDTO productCreateReqDTO, @RequestHeader("User-Idx") String userId, @RequestHeader("Username") String username) {
        return ResponseEntity.ok(productService.createProduct(productCreateReqDTO, Long.parseLong(userId), username));
    }

    @PutMapping(value = "/update/{productId}")
    @ApiOperation(value = "상품 수정", notes = "등록한 상품을 수정합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)", required = true),
            @ApiImplicitParam(name = "Username", value = "사용자 이름", required = true),
            @ApiImplicitParam(name = "productId", value = "상품 번호", required = true)
    })
    ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductUpdateReqDTO productUpdateReqDTO, @RequestHeader("User-Idx") String userId, @RequestHeader("Username") String username, @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.updateProduct(productUpdateReqDTO, Long.parseLong(userId), username, productId));
    }

    @DeleteMapping(value = "/delete/{productId}")
    @ApiOperation(value = "상품 삭제", notes = "등록한 상품을 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)", required = true),
            @ApiImplicitParam(name = "productId", value = "상품 번호", required = true)
    })
    void deleteProduct(@PathVariable("productId") Long productId, @RequestHeader("User-Idx") String userId) {
        productService.deleteProduct(productId, Long.parseLong(userId));
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "전체 상품 조회", notes = "전체 상품을 조회합니다.")
    ResponseEntity<ProductPageDTO> getProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getProductPage(pageable));
    }

    @GetMapping(value = "/myitems")
    @ApiOperation(value = "사용자 상품 조회", notes = "사용자가 등록한 상품을 조회합니다.")
    @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)", required = true)
    public ResponseEntity<ProductPageDTO> getUserProducts(@RequestHeader("User-Idx") String userId, Pageable pageable) {
        return ResponseEntity.ok(productService.getUserProductPage(Long.parseLong(userId), pageable));
    }

    @GetMapping(value = "/{brand}")
    @ApiOperation(value = "브랜드별 상품 조회", notes = "브랜드별로 상품을 조회합니다.")
    @ApiImplicitParam(name = "brand", value = "상품 브랜드 이름")
    ResponseEntity<ProductPageDTO> getBrandProducts(@PathVariable("brand") String brandName, Pageable pageable) {
        return ResponseEntity.ok(productService.getBrandProductPage(brandName, pageable));
    }

    @GetMapping(value = "/{status}")
    @ApiOperation(value = "상태별 상품 조회", notes = "등록된 상품의 상태별로 상품을 조회합니다.")
    @ApiImplicitParam(name = "status", value = "상품 상태")
    ResponseEntity<ProductPageDTO> getStatusProducts(@PathVariable("status") String status, Pageable pageable) {
        return ResponseEntity.ok(productService.getStatusProductPage(Product.StatusType.valueOf(status), pageable));
    }

    @GetMapping(value = "/{productId}")
    @ApiOperation(value = "상품 상세 조회", notes = "해당 상품의 상세 정보를 조회합니다.")
    @ApiImplicitParam(name = "productId", value = "상품 번호", required = true)
    ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.getProductDetail(productId));

    }

    @GetMapping(value = "/update/{productId}")
    @ApiOperation(value = "상품 상세 조회", notes = "수정하고자 하는 상품의 내용을 조회합니다.")
    @ApiImplicitParam(name = "productId", value = "상품 번호", required = true)
    ResponseEntity<ProductDTO> getUserProduct(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }

    @GetMapping(value = "")
    @ApiOperation(value = "경매 시간별 상품 조회", notes = "경매 시간별로 상품을 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "경매 시작 시간"),
            @ApiImplicitParam(name = "end", value = "경매 종료 시간")
    })
    ResponseEntity<ProductPageDTO> getProductsBetweenDatetime
            (@RequestParam("start") @DateTimeFormat(pattern = "yyyyMMdd")Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyyMMdd")Date end, Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsBetweenDatetimePage(start, end, pageable));
    }

    @PutMapping(value = "/cancel/{productId}")
    @ApiOperation(value = "경매 취소", notes = "등록한 상품의 경매를 취소합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "상품 번호"),
            @ApiImplicitParam(name = "User-Idx", value = "사용자 고유 식별 번호 (user_idx)")
    })
    ResponseEntity<ProductDTO> cancelProductBidding(@PathVariable("productId") Long productId, @RequestHeader("User-Idx") String userId) {
        return ResponseEntity.ok(productService.cancelProductBidding(productId, Long.parseLong(userId)));
    }

}
