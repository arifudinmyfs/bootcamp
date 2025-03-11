package com.springboot.bootcamp.Repositories;

import com.springboot.bootcamp.models.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    @Query("SELECT DISTINCT w.adm4 FROM WeatherEntity w")
    List<String> findAllAdm4Codes();
}
