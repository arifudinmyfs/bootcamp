package com.springboot.bootcamp.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Getter
@Setter
public class CreateAccountRequest {
    @NotNull(message = "User ID tidak boleh null")
    private UUID userId;

    @NotNull(message = "Balance tidak boleh null")
    private Double balance;
}
