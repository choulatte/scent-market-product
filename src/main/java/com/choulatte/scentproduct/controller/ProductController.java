package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "")
    List<ProductDTO> getProducts() {
        return productService.getProductsList();
    }

    @GetMapping(value = "/myitems")
    List<ProductDTO> getUserProducts(@RequestHeader("userId") String userId) {
        return productService.getUserProductsList(Long.parseLong(userId));
    }

    @GetMapping(value = "/interest")
    List<ProductDTO> getUserInterestingProducts(@RequestHeader("userId") String userId) {
        return productService.getUserInterestingProductList(Long.parseLong(userId));
    }

    @GetMapping(value = "/{brand}")
    List<ProductDTO> getBrandProducts(@PathVariable("brand") Long brandId) {
        return productService.getBrandProducts(brandId);
    }

    @GetMapping(value = "/{status}")
    List<ProductDTO> getBrandProducts(@PathVariable("status") String status) {
        return productService.getStatusProducts(status);
    }

    @GetMapping(value = "/{id}")
    ProductDTO getProduct(@PathVariable("id") Long id) {
        return productService.getProductDetail(id);
    }

    @GetMapping(value = "/update/{id}")
    ProductDTO getUserProduct(@PathVariable("id") Long id) {
        return productService.getUserProductDetail(id);
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
