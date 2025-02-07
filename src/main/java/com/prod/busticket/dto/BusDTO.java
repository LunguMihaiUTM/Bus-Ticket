package com.prod.busticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {
    private Long id;
    private String busNumber;
    private Integer capacity;
    private String type;
    private String companyName;

}
