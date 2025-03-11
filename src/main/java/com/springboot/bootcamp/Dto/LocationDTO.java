package com.springboot.bootcamp.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {
    private String provinsi;
    private String kotkab;
    private String kecamatan;
    private String desa;
    private double lon;
    private double lat;
    private String adm1;
    private String adm2;
    private String adm3;
    private String adm4;
}

