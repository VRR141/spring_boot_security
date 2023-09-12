--liquibase formatted sql

--changeset VRR:1

create table if not exists authorities
(
    id        bigserial primary key,
    authority varchar not null unique
);

create table if not exists role
(
    id         bigserial primary key,
    role_name  varchar not null unique,
    identifier uuid    not null unique
);

create table client
(
    id         bigserial primary key,
    identifier uuid    not null unique,
    login      varchar not null unique,
    password   varchar not null,
    is_active  boolean not null default (true)
);

create table client_role
(
    client_id bigint not null references client (id),
    role_id   bigint not null references role (id),
    primary key (client_id, role_id)
);

create table role_authority
(
    role_id      bigint not null references role (id),
    authority_id bigint not null references authorities (id),
    primary key (role_id, authority_id)
);
