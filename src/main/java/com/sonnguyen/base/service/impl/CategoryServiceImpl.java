package com.sonnguyen.base.service.impl;

import com.sonnguyen.base.dto.CategoryDto;
import com.sonnguyen.base.dto.in.CategoryDtoIn;
import com.sonnguyen.base.exception.BadRequestException;
import com.sonnguyen.base.exception.NotFoundException;
import com.sonnguyen.base.model.Category;
import com.sonnguyen.base.repository.CategoryRepository;
import com.sonnguyen.base.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDto> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDto::from);
    }

        @Override
        public CategoryDto getById(Long id) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục với id: " + id));
            return CategoryDto.from(category);
        }

    @Override
    public CategoryDto create(CategoryDtoIn categoryDtoIn) {
        if (categoryRepository.findByName(categoryDtoIn.getName()) != null) {
            throw new BadRequestException("Tên danh mục đã tồn tại");
        }

        Category category = new Category();
        category.setName(categoryDtoIn.getName());
        category.setDescription(categoryDtoIn.getDescription());
        return CategoryDto.from(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CategoryDtoIn categoryDtoIn) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục với id: " + id));

        if (categoryDtoIn.getName() != null && !categoryDtoIn.getName().isBlank()) {
            category.setName(categoryDtoIn.getName());
        }
        if (categoryDtoIn.getDescription() != null) {
            category.setDescription(categoryDtoIn.getDescription());
        }

        return CategoryDto.from(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục với id: " + id));
        categoryRepository.delete(category);
    }
}
