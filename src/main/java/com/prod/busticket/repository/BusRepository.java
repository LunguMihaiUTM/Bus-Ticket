package com.prod.busticket.repository;

import com.prod.busticket.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
