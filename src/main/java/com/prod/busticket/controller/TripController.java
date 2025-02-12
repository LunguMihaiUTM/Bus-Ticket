package com.prod.busticket.controller;

import com.prod.busticket.dto.TripDTO;
import com.prod.busticket.dto.request.TripFilterDTO;
import com.prod.busticket.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long tripId) {
        return ResponseEntity.ok(tripService.getTripById(tripId));
    }

    @PostMapping
    public ResponseEntity<List<TripDTO>> getTripsByFilter(@RequestBody(required = false) TripFilterDTO filter) {
        return ResponseEntity.ok(tripService.getTrips(filter));
    }
}
