package com.maddyverse.event_booking_api.repository;

import com.maddyverse.event_booking_api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
}
