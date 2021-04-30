create table person(
                       id serial primary key,
                       name varchar(128)
)

insert into person (name) values ('Mark');
insert into person (name) values ('Denis');
insert into person (name) values ('Anton');
insert into person (name) values ('Vladimir');
insert into person (name) values ('Alex');

create table product(
                        id serial primary key,
                        title varchar(128),
                        price int
)

insert into product(title, price) values ('Book', 40);
insert into product(title, price) values ('Computer', 100);
insert into product(title, price) values ('Cake', 5);
insert into product(title, price) values ('Boll', 10);
insert into product(title, price) values ('Milk', 18);
insert into product(title, price) values ('Bread', 20);
insert into product(title, price) values ('Car', 500);
insert into product(title, price) values ('Cake', 50);
insert into product(title, price) values ('Fish', 80);
insert into product(title, price) values ('T-shirt', 55);

select * from product;
select * from person;

create table role(
                     id uuid primary key,
                     name varchar(128)
)

alter table person add column role_id uuid;
alter table person add column login varchar(128);
alter table person add column password varchar(128);
alter table person add foreign key (role_id) references role(id);
insert into role (id, name) values ('cc839990-4997-4212-8709-e5eda3324994', 'ROLE_ADMIN');
insert into role(id, name) values('19b05a72-2c4d-4f0c-8eb8-c734db9ff542', 'ROLE_MANAGER');
insert into role(id, name) values('18c2f73d-e893-4c98-a871-e3928152ea00', 'ROLE_USER');
select * from person;

drop table orders cascade;
create table orders (
                        id bigserial primary key,
                        person_id bigint references person(id),
                        price int,
                        created_at timestamp default current_timestamp,
                        updated_at timestamp default current_timestamp
);

drop table order_items cascade;
create table order_items (
                             id bigserial primary key,
                             order_id bigint references orders(id),
                             product_id bigint references product(id),
                             title varchar(255),
                             quantity int,
                             price_per_product int,
                             price int,
                             created_at timestamp default current_timestamp,
                             updated_at timestamp default current_timestamp
);