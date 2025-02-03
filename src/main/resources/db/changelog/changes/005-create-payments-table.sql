create table payments
(
    payment_id     bigint           not null primary key,
    ticket_id      bigint           not null,
    user_id        bigint           not null,
    amount         double precision not null,
    payment_method varchar(20)      not null,
    payment_date   timestamp        not null default now(),
    status         varchar(20)      not null,
    CONSTRAINT FK_PAYMENT_TICKET FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id),
    CONSTRAINT FK_PAYMENT_USER FOREIGN KEY (user_id) REFERENCES users (user_id)
);

alter table payments owner to root;

create sequence payment_id_seq;

alter sequence payment_id_seq owner to root;
