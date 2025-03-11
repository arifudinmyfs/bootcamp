package com.springboot.bootcamp.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetailDTO {
    private String datetime;
    private int t; // Temperatur
    private String weather_desc; // Deskripsi Cuaca
    private double ws; // Kecepatan Angin
    private int hu; // Kelembaban
    private String image; // URL Icon Cuaca
}

