package com.sonnguyen.base.service.impl;

import com.sonnguyen.base.dto.ProductDto;
import com.sonnguyen.base.dto.in.ProductDtoIn;
import com.sonnguyen.base.exception.NotFoundException;
import com.sonnguyen.base.model.Category;
import com.sonnguyen.base.model.Product;
import com.sonnguyen.base.repository.CategoryRepository;
import com.sonnguyen.base.repository.ProductRepository;
import com.sonnguyen.base.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDto::from);
    }

    @Override
    public Page<ProductDto> getByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable).map(ProductDto::from);
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm với id: " + id));
        return ProductDto.from(product);
    }


    @Override
    public ProductDto create(ProductDtoIn productDtoIn) {
        Category category = categoryRepository.findById(productDtoIn.getCategoryId())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục với id: " + productDtoIn.getCategoryId()));

        Product product = new Product();
        product.setName(productDtoIn.getName());
        product.setDescription(productDtoIn.getDescription());
        product.setPrice(productDtoIn.getPrice());
        product.setQuantity(productDtoIn.getQuantity() != null ? productDtoIn.getQuantity() : 0);
        product.setCategory(category);

        return ProductDto.from(productRepository.save(product));
    }

    @Override
    public ProductDto update(Long id, ProductDtoIn productDtoIn) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm với id: " + id));

        if (productDtoIn.getName() != null && !productDtoIn.getName().isBlank()) {
            product.setName(productDtoIn.getName());
        }
        if (productDtoIn.getDescription() != null) {
            product.setDescription(productDtoIn.getDescription());
        }
        if (productDtoIn.getPrice() != null) {
            product.setPrice(productDtoIn.getPrice());
        }
        if (productDtoIn.getQuantity() != null) {
            product.setQuantity(productDtoIn.getQuantity());
        }
        if (productDtoIn.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDtoIn.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục"));
            product.setCategory(category);
        }

        return ProductDto.from(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm với id: " + id));
        productRepository.delete(product);
    }
}
