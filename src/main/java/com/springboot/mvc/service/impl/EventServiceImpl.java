package com.springboot.mvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.mvc.dto.EventDto;
import com.springboot.mvc.models.Club;
import com.springboot.mvc.models.Event;
import com.springboot.mvc.repository.ClubRepository;
import com.springboot.mvc.repository.EventRepository;
import com.springboot.mvc.service.EventService;
import com.springboot.mvc.mapper.EventMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {

        Club club = clubRepository.findById(clubId).get();
        Event event = EventMapper.mapToEvent(eventDto);

        event.setClub(club);
        eventRepository.save(event);

    }

    @Override
    public List<EventDto> findAllEvents() {

        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());

    }

    @Override
    public EventDto findEventById(Long eventId) {

        Event event = eventRepository.findById(eventId).get();
        return EventMapper.mapToEventDto(event);

    }

    @Override
    public void updateEvent(EventDto eventDto) {

        Event event = EventMapper.mapToEvent(eventDto);
        eventRepository.save(event);

    }

    @Override
    public void deleteEvent(Long eventId) {

        eventRepository.deleteById(eventId);

    }

}
