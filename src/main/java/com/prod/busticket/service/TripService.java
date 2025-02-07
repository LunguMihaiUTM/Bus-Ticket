package com.prod.busticket.service;

import com.prod.busticket.dto.TripDTO;
import com.prod.busticket.dto.request.TripFilterDTO;

import java.util.List;

public interface TripService {
    List<TripDTO> getTrips(TripFilterDTO filter);
    TripDTO getTripById(Long tripId);
}
