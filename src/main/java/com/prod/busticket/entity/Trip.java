package com.prod.busticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "trips")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_id_seq")
    @SequenceGenerator(name = "trip_id_seq", sequenceName = "trip_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "bus_id", foreignKey = @ForeignKey(name = "FK_TRIP_BUS"), nullable = false)
    private Long busId;

    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    @Column(name = "arrival_location", nullable = false)
    private String arrivalLocation;

    @Column(name = "departure_time", nullable = false)
    private java.time.LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private java.time.LocalDateTime arrivalTime;

    @Column(name = "price", nullable = false)
    private Double price;
}
