package com.springboot.bootcamp.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.bootcamp.Configs.KafkaProducer;
import com.springboot.bootcamp.Dto.WeatherResponseDTO;
import com.springboot.bootcamp.Repositories.WeatherRepository;
import com.springboot.bootcamp.models.WeatherEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final KafkaProducer weatherProducer;
    private final WeatherRepository weatherRepository;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplate restTemplate, KafkaProducer weatherProducer, WeatherRepository weatherRepository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.weatherProducer = weatherProducer;
        this.weatherRepository = weatherRepository;
        this.objectMapper = objectMapper;
    }

    public void fetchAndSaveWeatherData(String adm4) {
        // Bangun URL dengan request parameter
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.bmkg.go.id/publik/prakiraan-cuaca")
                .queryParam("adm4", adm4)
                .toUriString();

        // Ambil data dari API
        WeatherResponseDTO response = restTemplate.getForObject(url, WeatherResponseDTO.class);

        if (response != null && response.getData() != null) {
            response.getData().forEach(data -> {
                if (data.getCuaca() != null) {
                    List<WeatherEntity> weatherEntities = data.getCuaca().stream()
                            .flatMap(List::stream) // Karena cuaca berbentuk array dalam array
                            .map(cuaca -> {
                                WeatherEntity entity = new WeatherEntity();
                                entity.setAdm1(data.getLokasi().getAdm1());
                                entity.setAdm2(data.getLokasi().getAdm2());
                                entity.setAdm3(data.getLokasi().getAdm3());
                                entity.setAdm4(data.getLokasi().getAdm4());
                                entity.setProvinsi(data.getLokasi().getProvinsi());
                                entity.setKotkab(data.getLokasi().getKotkab());
                                entity.setKecamatan(data.getLokasi().getKecamatan());
                                entity.setDesa(data.getLokasi().getDesa());
                                entity.setLon(data.getLokasi().getLon());
                                entity.setLat(data.getLokasi().getLat());
                                // ✅ Konversi String ke LocalDateTime
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                                entity.setDatetime(LocalDateTime.parse(cuaca.getDatetime(), formatter));
                                entity.setTemperature(cuaca.getT());
                                entity.setWeather_desc(cuaca.getWeather_desc());
                                entity.setWind_speed(cuaca.getWs());
                                entity.setHumidity(cuaca.getHu());
                                entity.setImage(cuaca.getImage());
                                return entity;
                            })
                            .collect(Collectors.toList());

                    // Kirim ke Kafka
                    weatherRepository.saveAll(weatherEntities);
                    weatherEntities.forEach(result -> {
                        try {
                            weatherProducer.sendWeatherData("belajarkfk", " ", "message data yang di kirim" + objectMapper.writeValueAsString(result));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    });

                }
            });
        }
    }
}


