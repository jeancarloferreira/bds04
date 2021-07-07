package com.devsuperior.bds04.service;

import com.devsuperior.bds04.dto.CityDTO;

import java.util.List;

public interface CityService {

    CityDTO insert(CityDTO dto);

    List<CityDTO> findAll();
}
