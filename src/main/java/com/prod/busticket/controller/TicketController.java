package com.prod.busticket.controller;

import com.prod.busticket.dto.TicketDTO;
import com.prod.busticket.entity.Ticket;
import com.prod.busticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.getTicket(ticketId));
    }

    // one more

}
