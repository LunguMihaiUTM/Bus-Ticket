package com.prod.busticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @Column(name = "seat_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat_id_seq")
    @SequenceGenerator(name = "seat_id_seq", sequenceName = "seat_id_seq", allocationSize = 1)
    private Long seatId;

    @JoinColumn(name = "trip_id", foreignKey = @ForeignKey(name = "FK_SEAT_TRIP"), nullable = false)
    private Long tripId;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "is_booked", nullable = false)
    private Boolean isBooked;

    @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "FK_SEAT_TICKET"))
    private Long ticketId;
}

