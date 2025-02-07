package com.prod.busticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq")
    @SequenceGenerator(name = "ticket_id_seq", sequenceName = "ticket_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "trip_id", foreignKey = @ForeignKey(name = "FK_TICKET_TRIP"), nullable = false)
    private Long tripId;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_TICKET_USER"), nullable = false)
    private Long userId;

    @JoinColumn(name = "seat_id", foreignKey = @ForeignKey(name = "FK_TICKET_SEAT"), nullable = false)
    private Long seatId;

    @Column(name = "purchase_date", nullable = false)
    private java.time.LocalDateTime purchaseDate;

    @Column(name = "status", nullable = false)
    private String status;
}
