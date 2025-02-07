create table trips
(
    id                 bigint           not null primary key,
    bus_id             bigint           not null,
    departure_location varchar(100)     not null,
    arrival_location   varchar(100)     not null,
    departure_time     timestamp        not null,
    arrival_time       timestamp        not null,
    price              double precision not null,
    CONSTRAINT FK_TRIP_BUS FOREIGN KEY (bus_id) REFERENCES buses (id)
);

alter table trips owner to root;

create sequence trip_id_seq;

alter sequence trip_id_seq owner to root;
