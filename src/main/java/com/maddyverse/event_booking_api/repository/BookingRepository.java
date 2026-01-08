package com.maddyverse.event_booking_api.repository;

import com.maddyverse.event_booking_api.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
