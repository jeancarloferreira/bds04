package com.devsuperior.bds04.controller;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.repository.EventRepository;
import com.devsuperior.bds04.service.EventService;
import com.devsuperior.bds04.serviceImpl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @PostMapping
    public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto) {
        dto = eventService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable){
        Page<EventDTO> list = eventService.findAll(pageable);
        return  ResponseEntity.ok().body(list);
    }

}
