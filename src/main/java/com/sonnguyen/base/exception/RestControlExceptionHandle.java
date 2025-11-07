package com.sonnguyen.base.exception;

import com.sonnguyen.base.dto.res.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestControlExceptionHandle {
    @ExceptionHandler({CommonException.class})
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> resolveCommonException(CommonException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ApiResponse.builder()
                        .message(e.getMessage())
                        .success(false)
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .message("Dữ liệu không hợp lệ")
                .errors(errors)
                .success(false)
                .build());
    }


    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> resolveAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApiResponse.builder()
                        .message(e.getMessage())
                        .success(false)
                        .build()
        );
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> resolveAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApiResponse.builder()
                        .message("Access denied")
                        .success(false)
                        .build()
        );
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> resolveException(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApiResponse.builder()
                        .message(e.getMessage())
                        .success(false)
                        .build()
        );
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> resolveNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder()
                .message(e.getMessage())
                .success(false)
                .build());
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> resolveBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message(e.getMessage())
                .success(false)
                .build());
    }

}
