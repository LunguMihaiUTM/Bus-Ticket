package com.prod.busticket.service;

import com.prod.busticket.dto.TicketDTO;

public interface TicketService {
    TicketDTO getTicket(Long id);
    TicketDTO createTicket(Long tripId, Long seatId, Long userId);
}
