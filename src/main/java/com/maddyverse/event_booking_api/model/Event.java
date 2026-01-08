package com.maddyverse.event_booking_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

    @Id
    private Integer id;

    private String name;

    @Column(name = "available_slots")
    private Integer availableSlots;

    public Event(Integer id, String name, Integer availableSlots) {
        this.id = id;
        this.name = name;
        this.availableSlots = availableSlots;
    }

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }
}
