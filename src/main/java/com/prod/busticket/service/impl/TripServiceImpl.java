package com.prod.busticket.service.impl;

import com.prod.busticket.dto.BusDTO;
import com.prod.busticket.dto.SeatDTO;
import com.prod.busticket.dto.TripDTO;
import com.prod.busticket.dto.request.TripFilterDTO;
import com.prod.busticket.entity.Bus;
import com.prod.busticket.entity.Seat;
import com.prod.busticket.entity.Trip;
import com.prod.busticket.repository.BusRepository;
import com.prod.busticket.repository.SeatRepository;
import com.prod.busticket.repository.TripRepository;
import com.prod.busticket.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final BusRepository busRepository;
    private final SeatRepository seatRepository;

    @Override
    public TripDTO getTripById(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));

        Bus bus = busRepository.findById(trip.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found with id: " + trip.getBusId()));

        List<Seat> seats = seatRepository.findAllByTripId(tripId);

        List<SeatDTO> seatsDTO = seats.stream()
                .map(seat -> SeatDTO.builder()
                        .id(seat.getId())
                        .seatNumber(seat.getSeatNumber())
                        .isBooked(seat.getIsBooked())
                        .build())
                .toList();

        BusDTO busDTO = BusDTO.builder()
                .id(bus.getId())
                .busNumber(bus.getBusNumber())
                .type(bus.getType())
                .capacity(bus.getCapacity())
                .companyName(bus.getCompanyName())
                .build();

        return TripDTO.builder()
                .id(trip.getId())
                .price(trip.getPrice())
                .departureLocation(trip.getDepartureLocation())
                .arrivalLocation(trip.getArrivalLocation())
                .departureTime(trip.getDepartureTime())
                .arrivalTime(trip.getArrivalTime())
                .bus(busDTO)
                .seats(seatsDTO)
                .build();
    }


    @Override
    public List<TripDTO> getTrips(TripFilterDTO filter) {
        List<Trip> trips = tripRepository.findAll();
        List<Bus> buses = busRepository.findAll();

        List<BusDTO> busDTOs = buses.stream()
                .map( bus -> BusDTO.builder()
                        .id(bus.getId())
                        .busNumber(bus.getBusNumber())
                        .type(bus.getType())
                        .capacity(bus.getCapacity())
                        .companyName(bus.getCompanyName())
                        .build())
                .toList();

        if(filter != null && (StringUtils.hasText(filter.getLocation()))) {
            String searchLocation = filter.getLocation().toLowerCase();
            trips = trips.stream()
                    .filter(trip -> trip.getArrivalLocation().toLowerCase().contains(searchLocation) ||
                                    trip.getDepartureLocation().toLowerCase().contains(searchLocation))
                    .toList();
        }

        if(filter != null && filter.getDepartureTime() != null) {
            LocalDate filterDate = filter.getDepartureTime().toLocalDate();
            trips = trips.stream()
                    .filter(trip -> trip.getDepartureTime().toLocalDate().isEqual(filterDate))
                    .toList();
        }

        List<TripDTO> tripsDTO = trips.stream()
                .map(trip -> TripDTO.builder()
                        .id(trip.getId())
                        .price(trip.getPrice())
                        .departureLocation(trip.getDepartureLocation())
                        .arrivalLocation(trip.getArrivalLocation())
                        .departureTime(trip.getDepartureTime())
                        .arrivalTime(trip.getArrivalTime())
                        .bus(busDTOs.stream()
                                .filter(bus -> bus.getId().equals(trip.getBusId()))
                                .findFirst()
                                .orElse(null))
                        .seats(mapSeatByTripId(trip.getId()))
                        .build())
                .toList();

        return tripsDTO;
    }

    private List<SeatDTO> mapSeatByTripId(Long tripId) {
        List<Seat> seats = seatRepository.findAllByTripId(tripId);
        return seats.stream()
                .map(seat -> SeatDTO.builder()
                        .id(seat.getId())
                        .seatNumber(seat.getSeatNumber())
                        .isBooked(seat.getIsBooked())
                        .build())
                .toList();
    }
}
