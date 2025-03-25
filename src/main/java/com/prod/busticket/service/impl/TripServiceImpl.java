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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public TripDTO createTrip(TripDTO tripDTO) {

        Bus bus = busRepository.findById(tripDTO.getBus().getId())
                .orElseGet(() -> {
                    Bus newBus = Bus.builder()
                            .busNumber(tripDTO.getBus().getBusNumber())
                            .capacity(tripDTO.getBus().getCapacity())
                            .companyName(tripDTO.getBus().getCompanyName())
                            .type(tripDTO.getBus().getType())
                            .build();
                    return busRepository.save(newBus);
                });

        // Creăm trip-ul fără ID
        Trip trip = Trip.builder()
                .arrivalLocation(tripDTO.getArrivalLocation())
                .departureLocation(tripDTO.getDepartureLocation())
                .departureTime(tripDTO.getDepartureTime())
                .arrivalTime(tripDTO.getArrivalTime())
                .busId(bus.getId())
                .price(tripDTO.getPrice())
                .build();
        trip = tripRepository.save(trip);

        Trip finalTrip = trip;
        List<Seat> seats = tripDTO.getSeats().stream()
                .map(seat -> Seat.builder()
                        .seatNumber(seat.getSeatNumber())
                        .isBooked(seat.getIsBooked())
                        .tripId(finalTrip.getId())
                        .build())
                .toList();
        seatRepository.saveAll(seats);

        return getTripById(trip.getId());
    }

    @Override
    public String resetAllTrips(){
        List<Trip> trips = tripRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        int currentHour = now.getHour();
        int currentMinute = now.getMinute();

        for(Trip trip : trips) {

            LocalDateTime arrivalTime = trip.getArrivalTime();
            int tripArrivalHour = arrivalTime.getHour();
            int tripArrivalMinute = arrivalTime.getMinute();

            LocalDateTime departureTime = trip.getDepartureTime();
            int tripDepartureHour = departureTime.getHour();
            int tripDepartureMinute = departureTime.getMinute();

            if(currentHour > tripArrivalHour + 1) {

                resetSeatsByTripId(trip.getId());

                LocalDateTime tomorrowArrivalTime = now.plusDays(1).withHour(tripArrivalHour).withMinute(tripArrivalMinute).withSecond(0).withNano(0);
                LocalDateTime tomorrowDepartureTime = now.plusDays(1).withHour(tripDepartureHour).withMinute(tripDepartureMinute).withSecond(0).withNano(0);

                trip.setArrivalTime(tomorrowArrivalTime);
                trip.setDepartureTime(tomorrowDepartureTime);

                tripRepository.save(trip);
            }

        }

        return "Successfully Reset All Trips";
    }

    private void resetSeatsByTripId(Long tripId) {
        List<Seat> seats = seatRepository.findAllByTripId(tripId);
        if(!seats.isEmpty()){
            for(Seat seat : seats) {
                seat.setIsBooked(false);
            }
        }
        seatRepository.saveAll(seats);
    }

}
