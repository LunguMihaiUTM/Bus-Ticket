create table users
(
    id           bigint       not null primary key,
    first_name   varchar(50)  not null,
    last_name    varchar(50)  not null,
    email        varchar(100) not null unique,
    password     varchar(255) not null,
    phone_number varchar(20),
    created_at   timestamp    not null default now()
);

alter table users owner to root;

create sequence user_id_seq;

alter sequence user_id_seq owner to root;
