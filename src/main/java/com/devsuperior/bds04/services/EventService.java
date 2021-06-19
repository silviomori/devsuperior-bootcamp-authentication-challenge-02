package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable) {
		return eventRepository.findAll(pageable).map(entity -> new EventDTO(entity));
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		City cityEntity = cityRepository.getOne(dto.getCityId());
		Event entity = new Event(dto.getId(), dto.getName(), dto.getDate(), dto.getUrl(), cityEntity);
		entity = eventRepository.saveAndFlush(entity);
		dto = new EventDTO(entity);
		return dto;
	}

}
