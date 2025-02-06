package com.prod.busticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "payments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name = "payment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
    private Long paymentId;

    @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "FK_PAYMENT_TICKET"), nullable = false)
    private Long ticketId;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_PAYMENT_USER"), nullable = false)
    private Long userId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "payment_date", nullable = false)
    private java.time.LocalDateTime paymentDate;

    @Column(name = "status", nullable = false)
    private String status;
}
