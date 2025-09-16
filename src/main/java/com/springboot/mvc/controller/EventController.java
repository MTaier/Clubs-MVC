package com.springboot.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.springboot.mvc.dto.EventDto;
import com.springboot.mvc.models.Event;
import com.springboot.mvc.models.UserEntity;
import com.springboot.mvc.security.SecurityUtil;
import com.springboot.mvc.service.EventService;
import com.springboot.mvc.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable Long clubId, Model model) {

        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);

        return "events-create";

    }

    @PostMapping("/events/{clubId}/new")
    public String postMethodName(@PathVariable Long clubId, @ModelAttribute EventDto event, Model model,
            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "clubs-create";
        }

        eventService.createEvent(clubId, event);

        return "redirect:/clubs/" + clubId;

    }

    @GetMapping("/events")
    public String eventList(Model model) {

        UserEntity user = new UserEntity();
        List<EventDto> events = eventService.findAllEvents();
        String username = SecurityUtil.getSessionUser();

        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("user", user);
        model.addAttribute("events", events);

        return "events-list";

    }

    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable Long eventId, Model model) {

        UserEntity user = new UserEntity();
        EventDto event = eventService.findEventById(eventId);
        String username = SecurityUtil.getSessionUser();

        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("club", event);
        model.addAttribute("user", user);
        model.addAttribute("event", event);

        return "events-detail";

    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable Long eventId, Model model) {

        EventDto event = eventService.findEventById(eventId);
        model.addAttribute("event", event);

        return "events-edit";

    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable Long eventId, @Valid @ModelAttribute("event") EventDto event,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "events-edit";
        }

        EventDto eventDto = eventService.findEventById(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);

        return "redirect:/events/";

    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable Long eventId) {

        eventService.deleteEvent(eventId);

        return "redirect:/events";

    }

}
