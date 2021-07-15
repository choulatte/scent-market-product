package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import com.choulatte.scentproduct.dto.ProductPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/register")
    void setProduct(@RequestBody ProductDTO productDTO, @RequestHeader("userId") String userIdx, @RequestHeader("username") String username) {
        productService.createProduct(productDTO, Long.parseLong(userIdx), username);
    }

    @PutMapping(value = "/update/{id}")
    void updateProduct(@RequestBody ProductDTO productDTO, @RequestHeader("userId") String userIdx, @RequestHeader("username") String username, @PathVariable("id") Long id) {
        productService.updateProduct(productDTO, Long.parseLong(userIdx), username, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    void deleteProduct(@PathVariable("id") Long id, @RequestHeader("userId") String userId) {
        productService.deleteProduct(id, Long.parseLong(userId));
    }

    @GetMapping(value = "")
    ResponseEntity<ProductPageDTO> getProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getProductPage(pageable));
    }

    @GetMapping(value = "/myitems")
    public ResponseEntity<ProductPageDTO> getUserProducts(@RequestHeader("userId") String userId, Pageable pageable) {
        return ResponseEntity.ok(productService.getUserProductPage(Long.parseLong(userId), pageable));
    }

    @GetMapping(value = "/{brand}")
    ResponseEntity<ProductPageDTO> getBrandProducts(@PathVariable("brand") Long brandId, Pageable pageable) {
        return ResponseEntity.ok(productService.getBrandProductPage(brandId, pageable));
    }

    @GetMapping(value = "/{status}")
    ResponseEntity<ProductPageDTO> getBrandProducts(@PathVariable("status") String status, Pageable pageable) {
        return ResponseEntity.ok(productService.getStatusProductPage(StatusType.valueOf(status), pageable));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        return productService.getProductDetail(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/update/{id}")
    ResponseEntity<ProductDTO> getUserProduct(@PathVariable("id") Long id) {
        return productService.getProductDetail(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "")
    ResponseEntity<ProductPageDTO> getProductsBetweenDatetime
            (@RequestParam("start") @DateTimeFormat(pattern = "yyyyMMdd")Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyyMMdd")Date end, Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsBetweenDatetimePage(start, end, pageable));
    }

}
