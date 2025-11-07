package com.sonnguyen.base.dto.in;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDtoIn {

    @NotNull(message = "Tên sản phẩm không được để trống")
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s\\-]*$",
            message = "Tên sản phẩm không được chứa kí tự đặc biệt (@, #, %, ...)"
    )
    @Size(min = 3, max = 50, message = "Tên sản phẩm phải có độ dài từ 3 đến 50 ký tự")
    private String name;

    @Size(max = 255, message = "Mô tả không được vượt quá 255 ký tự")
    private String description;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá sản phẩm phải lớn hơn 0")
    private Double price;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    @Max(value = 1000, message = "Số lượng không được vượt quá 1000")
    private Integer quantity;

    @NotNull(message = "ID danh mục không được để trống")
    private Long categoryId;
}
