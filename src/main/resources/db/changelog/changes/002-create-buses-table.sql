create table buses
(
    id           bigint       not null primary key,
    bus_number   varchar(50)  not null unique,
    capacity     int          not null,
    type         varchar(50)  not null,
    company_name varchar(100) not null
);

alter table buses owner to root;

create sequence bus_id_seq;

alter sequence bus_id_seq owner to root;
