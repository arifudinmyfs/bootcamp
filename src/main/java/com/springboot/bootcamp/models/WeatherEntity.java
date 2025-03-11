package com.springboot.bootcamp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "weather_forecast_arifudin")
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adm1;
    private String adm2;
    private String adm3;
    private String adm4;
    private String provinsi;
    private String kotkab;
    private String kecamatan;
    private String desa;
    private double lon;
    private double lat;
    @Column(name = "datetime", columnDefinition = "TIMESTAMP")
    private LocalDateTime datetime; // Ubah ke LocalDateTime
    private int temperature;
    private String weather_desc;
    private double wind_speed;
    private int humidity;
    private String image;
}

