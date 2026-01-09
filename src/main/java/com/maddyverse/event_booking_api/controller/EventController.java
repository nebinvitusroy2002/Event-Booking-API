package com.maddyverse.event_booking_api.controller;

import com.maddyverse.event_booking_api.service.EventService;
import com.maddyverse.event_booking_api.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public Map<String, Object> getAllEvents() {

        List<Event> events = service.getAllEvents();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("events", events);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Integer id) {
        try {
            Event event = service.getEventById(id);
            return ResponseEntity.ok(event);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "success", false,
                            "message", "No such event exist"
                    )
            );
        }
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<?> bookTickets(
            @PathVariable Integer id,
            @RequestBody Map<String, Integer> request) {

        try {
            Integer quantity = request.get("quantity");
            Event event = service.bookTickets(id, quantity);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "🎉 Booked " + quantity + " tickets!",
                    "remaining_slots", event.getAvailableSlots()
            ));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }
    
}
