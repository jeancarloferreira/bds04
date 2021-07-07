package com.devsuperior.bds04.service;

import com.devsuperior.bds04.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    EventDTO save(EventDTO dto);

    Page<EventDTO> findAll(Pageable pageable);
}
