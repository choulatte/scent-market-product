package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "")
    ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProductsList());
    }

    @GetMapping(value = "/myitems")
    ResponseEntity<List<ProductDTO>> getUserProducts(@RequestHeader("userId") String userId) {
        return ResponseEntity.ok(productService.getUserProductsList(Long.parseLong(userId)));
    }

    @GetMapping(value = "/{brand}")
    ResponseEntity<List<ProductDTO>> getBrandProducts(@PathVariable("brand") Long brandId) {
        return ResponseEntity.ok(productService.getBrandProducts(brandId));
    }

    @GetMapping(value = "/{status}")
    ResponseEntity<List<ProductDTO>> getBrandProducts(@PathVariable("status") String status) {
        return ResponseEntity.ok(productService.getStatusProducts(StatusType.valueOf(status)));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        return productService.getProductDetail(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/update/{id}")
    ResponseEntity<ProductDTO> getUserProduct(@PathVariable("id") Long id) {
        return productService.getUserProductDetail(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/register")
    void setProduct(@RequestBody ProductDTO productDTO, @RequestHeader("userId") String userIdx, @RequestHeader("username") String username) {
        productService.setProduct(productDTO, Long.parseLong(userIdx), username);
    }

    @PutMapping(value = "/update/{id}")
    void updateProduct(@RequestBody ProductDTO productDTO, @RequestHeader("userId") String userIdx, @RequestHeader("username") String username, @PathVariable("id") Long id) {
        productService.setProduct(productDTO, Long.parseLong(userIdx), username, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
