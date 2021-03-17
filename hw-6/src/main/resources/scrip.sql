create table person(
id serial primary key,
name varchar(128)
)

insert into product(name, price) values ('Book', 40);
insert into product(name, price) values ('Computer', 100);
insert into product(name, price) values ('Cake', 5);
insert into product(name, price) values ('boll', 10);

select * from product;

insert into person (name) values ('Dmitri');
insert into person (name) values ('Denis');
insert into person (name) values ('Anton');
insert into person (name) values ('Vladimir');