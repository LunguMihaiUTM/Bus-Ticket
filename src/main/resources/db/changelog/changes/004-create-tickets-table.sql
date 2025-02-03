create table tickets
(
    ticket_id      bigint           not null primary key,
    trip_id        bigint           not null,
    user_id        bigint           not null,
    seat_number    int              not null,
    purchase_date  timestamp        not null default now(),
    status         varchar(20)      not null,
    CONSTRAINT FK_TICKET_TRIP FOREIGN KEY (trip_id) REFERENCES trips (trip_id),
    CONSTRAINT FK_TICKET_USER FOREIGN KEY (user_id) REFERENCES users (user_id)
);

alter table tickets owner to root;

create sequence ticket_id_seq;

alter sequence ticket_id_seq owner to root;
