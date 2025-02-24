package com.springboot.bootcamp.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> {
    private T data;

    public DataResponse(String status, String message, T data) {
        this.data = data;
    }
}
