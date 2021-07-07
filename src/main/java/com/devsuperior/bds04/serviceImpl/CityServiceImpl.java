package com.devsuperior.bds04.serviceImpl;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entity.City;
import com.devsuperior.bds04.repository.CityRepository;
import com.devsuperior.bds04.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    @Transactional
    public CityDTO insert(CityDTO dto) {

        City entity = new City();
        copyDtoToEntity(dto, entity);
        entity = cityRepository.save(entity);

        return new CityDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll(Sort.by("name"));
        List<CityDTO> listDto = cities.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
        return listDto;
    }

    private void copyDtoToEntity(CityDTO dto, City entity){
        entity.setName(dto.getName());
    }
}
