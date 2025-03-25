INSERT INTO tickets (id, trip_id, user_id, seat_id, purchase_date, status)
VALUES
    (nextval('ticket_id_seq'), 1, 1, 3, '2025-02-01', 'CONFIRMED'),
    (nextval('ticket_id_seq'), 2, 2, 4, '2025-02-02', 'CONFIRMED'),
    (nextval('ticket_id_seq'), 3, 3, 7, '2025-02-03', 'CANCELED'),
    (nextval('ticket_id_seq'), 3, 4, 9, '2025-02-04', 'CONFIRMED'),
    (nextval('ticket_id_seq'), 4, 5, 12, '2025-02-05', 'CONFIRMED'),
    (nextval('ticket_id_seq'), 5, 6, 14, '2025-02-06', 'CONFIRMED'),
    (nextval('ticket_id_seq'), 6, 7, 16, '2025-02-07', 'CANCELED'),
    (nextval('ticket_id_seq'), 6, 8, 18, '2025-02-08', 'CONFIRMED'),
    (nextval('ticket_id_seq'), 7, 9, 21, '2025-02-09', 'CONFIRMED'),
    (nextval('ticket_id_seq'), 5, 10, 15, '2025-02-10', 'CONFIRMED');
