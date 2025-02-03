create table seats
(
    seat_id       bigint           not null primary key,
    trip_id       bigint           not null,
    seat_number   int              not null,
    is_booked     boolean          not null default false,
    ticket_id     bigint           default null,
    CONSTRAINT FK_SEAT_TRIP FOREIGN KEY (trip_id) REFERENCES trips (trip_id),
    CONSTRAINT FK_SEAT_TICKET FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id),
    unique (trip_id, seat_number)
);

alter table seats owner to root;

create sequence seat_id_seq;

alter sequence seat_id_seq owner to root;
