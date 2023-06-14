create sequence dish_id_seq;

alter sequence dish_id_seq owner to postgres;

create sequence user_id_seq;

alter sequence user_id_seq owner to postgres;

create sequence order_id_seq;

alter sequence order_id_seq owner to postgres;

create table dishes
(
    id      bigint       not null
        constraint dishes_pkey
            primary key,
    name_en varchar(255) not null,
    name_ua varchar(255) not null,
    portion bigint       not null,
    price   bigint       not null,
    constraint ukfikndnq1jqfynutve4la6g26d
        unique (name_en, name_ua)
);

alter table dishes
    owner to postgres;

create table order_units
(
    id       bigserial not null
        constraint order_units_pkey
            primary key,
    quantity integer,
    dish_id  bigint
        constraint fkn7gbg3ewak2glauej323cr5gv
            references dishes
);

alter table order_units
    owner to postgres;

create table users
(
    id                      bigint       not null
        constraint users_pkey
            primary key,
    account_non_expired     boolean      not null,
    account_non_locked      boolean      not null,
    credentials_non_expired boolean      not null,
    enabled                 boolean      not null,
    funds                   bigint       not null
        constraint users_funds_check
            check ((funds >= 0) AND (funds <= 1000000)),
    name_en                 varchar(255) not null,
    name_ua                 varchar(255) not null,
    orders_number           integer      not null,
    orders_total_cost       bigint       not null,
    password                varchar(100) not null,
    registration_date       date         not null,
    username                varchar(255) not null
        constraint ukr43af9ap4edm43mmtq01oddj6
            unique
);

alter table users
    owner to postgres;

create table orders
(
    id       bigint not null
        constraint orders_pkey
            primary key,
    accepted timestamp,
    created  timestamp,
    paid     timestamp,
    ready    timestamp,
    status   integer,
    user_id  bigint
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

alter table orders
    owner to postgres;

create table orders_order_units
(
    order_id       bigint not null
        constraint fk9ge5xtk3p935pjf4q63ub430w
            references orders,
    order_units_id bigint not null
        constraint uk_bko4xi81q6qep4mdn8prx7b4d
            unique
        constraint fkqorf08f7s0ggv71o3fu7o010j
            references order_units
);

alter table orders_order_units
    owner to postgres;

create table user_authorities
(
    user_id     bigint not null
        constraint fkhiiib540jf74gksgb87oofni
            references users,
    authorities integer
);

alter table user_authorities
    owner to postgres;

insert into restaurant.dishes (id, name_en, name_ua, portion, price) VALUES (1,'Salviar','Семга',50,1000);
insert into restaurant.dishes (id, name_en, name_ua, portion, price) VALUES (2,'Mussels','Мидии',500,2000);