package com.springboot.bootcamp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "CRUD_ARIFUDIN")
public class Crud {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
