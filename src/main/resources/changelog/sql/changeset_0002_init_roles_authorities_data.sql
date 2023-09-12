--liquibase formatted sql

--changeset VRR:2

insert into role(role_name, identifier)
values ('USER', gen_random_uuid()),
       ('ADMIN', gen_random_uuid()),
       ('OWNER', gen_random_uuid());

insert into authorities(authority)
values ('READ'),
       ('WRITE'),
       ('GRANT_ROLES');

insert into role_authority (role_id, authority_id)
values ((select r.id from role r where role_name = 'USER'),
        (select a.id from authorities a where a.authority = 'READ')),
       ((select r.id from role r where role_name = 'ADMIN'),
        (select a.id from authorities a where a.authority = 'WRITE')),
       ((select r.id from role r where role_name = 'OWNER'),
        (select a.id from authorities a where a.authority = 'GRANT_ROLES'));