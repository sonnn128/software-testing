package com.sonnguyen.base.dto.in;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CategoryDtoIn {

    @NotBlank(message = "Tên danh mục không được để trống")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s\\-]*$",
            message = "Tên danh mục không được chứa kí tự đặc biệt (@, #, %, ...)"
    )
    @Size(min = 3, max = 50, message = "Tên danh mục phải có độ dài từ 3 đến 50 ký tự")
    private String name;

    @Size(max = 255, message = "Mô tả không được vượt quá 255 ký tự")
    private String description;
}

