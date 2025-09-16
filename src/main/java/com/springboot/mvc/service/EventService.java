package com.springboot.mvc.service;

import java.util.List;

import com.springboot.mvc.dto.EventDto;

public interface EventService {

    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(Long eventId);

    void updateEvent(EventDto eventDto);

    void deleteEvent(Long eventId);

}
