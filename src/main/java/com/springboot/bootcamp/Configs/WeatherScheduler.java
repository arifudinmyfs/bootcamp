package com.springboot.bootcamp.Configs;

import com.springboot.bootcamp.Repositories.WeatherRepository;
import com.springboot.bootcamp.Services.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherScheduler {
    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;

    public WeatherScheduler(WeatherService weatherService, WeatherRepository weatherRepository) {
        this.weatherService = weatherService;
        this.weatherRepository = weatherRepository;
    }

//    @Scheduled(cron = "0 */1 * * * ?") // Eksekusi setiap 30 menit
//    public void scheduleFetchWeatherData() {
//        List<String> adm4List = weatherRepository.findAll();
//
//        for (String adm4 : adm4List) {
//            weatherService.fetchAndSaveWeatherData(adm4);
//        }
//    }
}

