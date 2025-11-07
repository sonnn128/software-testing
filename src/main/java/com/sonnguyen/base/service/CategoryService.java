package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.CategoryDto;
import com.sonnguyen.base.dto.in.CategoryDtoIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> getAll(Pageable pageable);
    CategoryDto getById(Long id);
    CategoryDto create(CategoryDtoIn categoryDtoIn);
    CategoryDto update(Long id, CategoryDtoIn categoryDtoIn);
    void delete(Long id);
}
