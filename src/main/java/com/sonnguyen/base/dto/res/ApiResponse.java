package com.sonnguyen.base.dto.res;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Map<String, String> errors;
}

