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