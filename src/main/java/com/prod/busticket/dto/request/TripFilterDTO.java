package com.prod.busticket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripFilterDTO {
    private String location;
    private java.time.LocalDateTime departureTime;
}
