package com.springboot.bootcamp.Controllers;

import com.springboot.bootcamp.Services.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchWeatherData(@RequestParam String adm4) {
        if (adm4 == null || adm4.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Parameter 'adm4' tidak boleh kosong");
        }

        try {
            weatherService.fetchAndSaveWeatherData(adm4);
            return ResponseEntity.ok("Weather data fetched and saved for adm4: " + adm4);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching weather data: " + e.getMessage());
        }
    }
}

