package com.springboot.bootcamp.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ResponseAcount {
    private UUID id;
    private UUID userId;
    private Double balance;
}
