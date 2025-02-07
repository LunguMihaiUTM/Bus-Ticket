package com.prod.busticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "buses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_id_seq")
    @SequenceGenerator(name = "bus_id_seq", sequenceName = "bus_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "bus_number", nullable = false, unique = true)
    private String busNumber;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "company_name", nullable = false)
    private String companyName;
}
