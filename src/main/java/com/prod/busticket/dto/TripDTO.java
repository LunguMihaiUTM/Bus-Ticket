package com.prod.busticket.dto;

import com.prod.busticket.entity.Seat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {
    private Long id;
    private String departureLocation;
    private String arrivalLocation;
    private java.time.LocalDateTime departureTime;
    private java.time.LocalDateTime arrivalTime;
    private Double price;

    private BusDTO bus;
    private List<SeatDTO> seats;
    //private TicketDTO ticket;
}
