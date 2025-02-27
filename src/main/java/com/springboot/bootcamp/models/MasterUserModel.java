package com.springboot.bootcamp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "MASTER_USER_ARIFUDIN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;
}