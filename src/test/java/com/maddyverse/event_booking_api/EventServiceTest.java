package com.maddyverse.event_booking_api;

import com.maddyverse.event_booking_api.model.Event;
import com.maddyverse.event_booking_api.repository.BookingRepository;
import com.maddyverse.event_booking_api.repository.EventRepository;
import com.maddyverse.event_booking_api.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void bookTickets_successfulBooking_reducesAvailableSlots() {

        Event event = new Event(1, "Concert", 10);
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenAnswer(i -> i.getArgument(0));
        when(bookingRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Event updatedEvent = eventService.bookTickets(1, 3);

        assertEquals(7, updatedEvent.getAvailableSlots());
        verify(eventRepository).save(event);
        verify(bookingRepository).save(any());
    }

    @Test
    void bookTickets_negativeQuantity_throwsException() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> eventService.bookTickets(1, -2)
        );

        assertEquals("Quantity must be greater than zero", exception.getMessage());
        verify(eventRepository, never()).save(any());
    }

    @Test
    void bookTickets_insufficientSlots_throwsException() {

        Event event = new Event(1, "Concert", 2);
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> eventService.bookTickets(1, 5)
        );

        assertTrue(exception.getMessage().contains("Only 2 tickets available"));
        verify(eventRepository, never()).save(any());
    }
}
