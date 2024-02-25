package com.wanted.preonboarding.ticket.reservation.infrastructure.repository;

import com.wanted.preonboarding.ticket.reservation.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findByNameAndPhoneNumber(String name, String phoneNumber);
    Reservation findByName(String name);
    Optional<Reservation> findByPerformanceId(UUID performanceId);
}
