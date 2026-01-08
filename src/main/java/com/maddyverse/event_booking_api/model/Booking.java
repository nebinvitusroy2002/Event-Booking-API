package com.maddyverse.event_booking_api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    private Integer quantity;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    public Booking(Integer eventId, Integer quantity) {
        this.eventId = eventId;
        this.quantity = quantity;
        this.bookedAt = LocalDateTime.now();
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }
}
