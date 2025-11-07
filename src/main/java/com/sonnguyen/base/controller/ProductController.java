package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.in.ProductDtoIn;
import com.sonnguyen.base.dto.res.ApiResponse;
import com.sonnguyen.base.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {

        Sort.Direction direction = Sort.Direction.fromString(order.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get all products successfully")
                        .data(productService.getAll(pageable))
                        .build()
        );
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get products by category successfully")
                        .data(productService.getByCategoryId(categoryId, pageable))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get product successfully")
                        .data(productService.getById(id))
                        .build()
        );
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDtoIn productDtoIn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .success(true)
                        .message("Create product successfully")
                        .data(productService.create(productDtoIn))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDtoIn productDtoIn) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Update product successfully")
                        .data(productService.update(id, productDtoIn))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaSanPham(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Xóa sản phẩm thành công")
                        .build()
        );
    }
}
