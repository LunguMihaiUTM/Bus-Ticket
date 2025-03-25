package com.prod.busticket.service.impl;

import com.prod.busticket.dto.SeatDTO;
import com.prod.busticket.dto.TicketDTO;
import com.prod.busticket.dto.UserDTO;
import com.prod.busticket.entity.Seat;
import com.prod.busticket.entity.Ticket;
import com.prod.busticket.entity.User;
import com.prod.busticket.repository.SeatRepository;
import com.prod.busticket.repository.TicketRepository;
import com.prod.busticket.repository.UserRepository;
import com.prod.busticket.service.TicketService;
import com.prod.busticket.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final TripService tripService;

    @Override
    public TicketDTO getTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));

        return TicketDTO.builder()
                .id(ticket.getId())
                .seat(getSeatById(ticket.getSeatId()))
                .trip(tripService.getTripById(ticket.getTripId()))
                .user(getUserById(ticket.getUserId()))
                .build();
    }

    private SeatDTO getSeatById(Long id){
        Seat seat = seatRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        return SeatDTO.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .isBooked(seat.getIsBooked())
                .build();
    }

    private UserDTO getUserById(Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();
    }

    @Override
    public TicketDTO createTicket(Long tripId, Long seatId, Long userId){

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + seatId));;

        if(seat.getIsBooked()) {
            throw new RuntimeException("Seat already booked");
        }

        Ticket ticket = Ticket.builder()
                .tripId(tripId)
                .seatId(seatId)
                .userId(userId)
                .purchaseDate(LocalDateTime.now())
                .status("SUCCESS")
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);

        return getTicket(savedTicket.getId());
    }


}
