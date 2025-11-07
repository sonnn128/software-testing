package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.ProductDto;
import com.sonnguyen.base.dto.in.ProductDtoIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDto> getAll(Pageable pageable);
    Page<ProductDto> getByCategoryId(Long categoryId, Pageable pageable);
    ProductDto getById(Long id);
    ProductDto create(ProductDtoIn productDtoIn);
    ProductDto update(Long id, ProductDtoIn productDtoIn);
    void delete(Long id);
}
