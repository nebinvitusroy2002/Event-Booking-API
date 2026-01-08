package com.maddyverse.event_booking_api.service;

import com.maddyverse.event_booking_api.model.Booking;
import com.maddyverse.event_booking_api.model.Event;
import com.maddyverse.event_booking_api.repository.BookingRepository;
import com.maddyverse.event_booking_api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private final BookingRepository bookingRepository;


    public EventService(EventRepository repository,BookingRepository bookingRepository) {
        this.eventRepository = repository;
        this.bookingRepository = bookingRepository;
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such event exist"));
    }

    public Event bookTickets(Integer eventId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Event event = getEventById(eventId);

        if (event.getAvailableSlots() < quantity) {
            throw new IllegalArgumentException(
                    "Only " + event.getAvailableSlots() +
                            " tickets available, but you asked for " + quantity + "!"
            );
        }

        event.setAvailableSlots(event.getAvailableSlots() - quantity);
        eventRepository.save(event);

        Booking booking = new Booking(eventId, quantity);
        bookingRepository.save(booking);

        return event;
    }
}
