package com.springboot.bootcamp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "MASTER_ACCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterAccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private MasterUserModel user;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;
}