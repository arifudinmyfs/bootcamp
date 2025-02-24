package com.springboot.bootcamp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("timestamp", LocalDateTime.now());
        HttpStatus status;
        String errorMessage;

        if (ex instanceof CustomException) {
            // Jika error berasal dari logic kita
            CustomException ce = (CustomException) ex;
            status = ce.getStatus();
            errorMessage = ce.getMessage();
        } else if (ex instanceof ResponseStatusException) {
            // Jika error menggunakan ResponseStatusException
            ResponseStatusException rse = (ResponseStatusException) ex;
            status = (HttpStatus) rse.getStatusCode();
            errorMessage = rse.getReason();
        } else if (ex instanceof MethodArgumentNotValidException) {
            // Jika error karena validasi @Valid di Request Body
            status = HttpStatus.BAD_REQUEST;
            errorMessage = "Validation Failed";

            // Mengumpulkan semua pesan validasi yang gagal
            Map<String, String> errors = new HashMap<>();
            ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            response.put("errors", errors);
        } else {
            // Default jika error tidak diketahui
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            errorMessage = "Terjadi kesalahan yang tidak terduga.";
        }

        response.put("message", errorMessage);

        return new ResponseEntity<>(response, status);
    }

//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
//        Map<String, Object> response = new LinkedHashMap<>();
////        response.put("timestamp", LocalDateTime.now());
//        response.put("status", ex.getStatusCode().value());
//        response.put("error", ex.getReason());
//        response.put("message", ex.getReason());
//
//        return new ResponseEntity<>(response, ex.getStatusCode());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, Object> response = new LinkedHashMap<>();
////        response.put("timestamp", LocalDateTime.now());
//
//        response.put("status", HttpStatus.BAD_REQUEST.value());
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage())
//        );
//        response.put("errors", errors);
//        return ResponseEntity.badRequest().body(response);
//    }

}
