package com.prod.busticket.repository;

import com.prod.busticket.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByTripId(Long tripId);
}
