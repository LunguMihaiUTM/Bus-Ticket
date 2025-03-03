package com.prod.busticket.dto;

import com.prod.busticket.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private SeatDTO seat;
    private TripDTO trip;
    private UserDTO user;
}
