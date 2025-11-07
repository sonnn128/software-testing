package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.in.CategoryDtoIn;
import com.sonnguyen.base.dto.res.ApiResponse;
import com.sonnguyen.base.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {

        Sort.Direction direction = Sort.Direction.fromString(order.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get all categories successfully")
                        .data(categoryService.getAll(pageable))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Get category successfully")
                        .data(categoryService.getById(id))
                        .build()
        );
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDtoIn categoryDtoIn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .success(true)
                        .message("Create category successfully")
                        .data(categoryService.create(categoryDtoIn))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDtoIn categoryDtoIn) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Update category successfully")
                        .data(categoryService.update(id, categoryDtoIn))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaDanhMuc(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Xóa danh mục thành công với id: " + id)
                        .build()
        );
    }
}
