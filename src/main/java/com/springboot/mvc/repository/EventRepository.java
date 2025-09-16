package com.springboot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mvc.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
