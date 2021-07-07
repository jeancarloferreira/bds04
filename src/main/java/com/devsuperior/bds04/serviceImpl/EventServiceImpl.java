package com.devsuperior.bds04.serviceImpl;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entity.City;
import com.devsuperior.bds04.entity.Event;
import com.devsuperior.bds04.repository.CityRepository;
import com.devsuperior.bds04.repository.EventRepository;
import com.devsuperior.bds04.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public EventDTO save(EventDTO dto) {
        Event entity = new Event();
        copyDtoToEntity(dto, entity);
        entity = eventRepository.save(entity);
        return new EventDTO(entity);
    }

    @Override
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> page = eventRepository.findAll(pageable);
        Page<EventDTO> pageDto = page.map(event -> new EventDTO(event));
        return pageDto;
    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {
        entity.setDate(dto.getDate());
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());

        City city = cityRepository.getOne(dto.getCityId());

        entity.setCity(city);
    }
}
