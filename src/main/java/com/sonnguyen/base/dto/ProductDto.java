package com.sonnguyen.base.dto;

import com.sonnguyen.base.model.Product;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
