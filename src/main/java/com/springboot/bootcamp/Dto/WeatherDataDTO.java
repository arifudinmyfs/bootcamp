package com.springboot.bootcamp.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataDTO {
    private LocationDTO lokasi;
    private List<List<WeatherDetailDTO>> cuaca;
}

