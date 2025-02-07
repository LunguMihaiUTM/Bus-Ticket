create table seats
(
    id          bigint  not null primary key,
    trip_id     bigint  not null,
    seat_number int     not null,
    is_booked   boolean not null default false,
    CONSTRAINT FK_SEAT_TRIP FOREIGN KEY (trip_id) REFERENCES trips (id)
);

alter table seats owner to root;

create sequence seat_id_seq;

alter sequence seat_id_seq owner to root;
